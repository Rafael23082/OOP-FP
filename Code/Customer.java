import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

// Class that processes the actions of a customer
final public class Customer extends User{

    String customerID, phoneNumber;
    // Customer constructor with containing the attributes of user and additional attributes
    public Customer(String firstName, String lastName, String phoneNumber, String password){

        super(firstName, lastName, password);
        this.phoneNumber = phoneNumber;
        this.customerID = "CUS" + Main.customerList.size();
    }

     // Method that displays the car information of the cars present in the array
    public static void displayCarInformation(Car[] cars){

        UIManager.put("OptionPane.messageFont", new Font("Monospaced", Font.BOLD, 15)); // Make all letters occupy equal space

        // String builder is used as the information will be displayed using JOptionPane
        StringBuilder print = new StringBuilder("\nCar ID:       Car Brand:    Car Model:    Color:        Year:         Mileage:      Price/Day:    Status:\n");

        for (Car C : cars){

            String available;
            if (C.isAvailable()) available = "Available"; // So it will not display true or false
            else available = "Unavailable";

            print.append("\n");

            // List of features
            String[] lengths = new String[]{C.getID(), C.getBrand(), C.getModel(), C.getColor(), String.valueOf(C.getYear()), String.valueOf(C.getMileage()), String.valueOf(C.getPricePerDay()), available};

            // Goes through all the features and adds space depending on the length of the string to create a structured printing
            for (String s : lengths){
                print.append(s);
                print.append(" ".repeat(14 - s.length()));
            }
        }

        JOptionPane.showMessageDialog(null, print.toString()); // Displays string
    }

    // Search Car Page
    public static void searchCarPage(Customer customer){

        // Creates frame and panel
        Frame frame = new Frame();
        Panel panel = new Panel(340, 200, 550, 300);

        // Adds return button that redirects the user back to the action page of the customer
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> {
            User.actionPage(customer);
            frame.dispose();
        });

        Label searchHeader = new Label("Search Car By:", 0, 0, 550, 100, 20, true); // Header

        // Brand button
        JButton brandButton = new JButton("Brand");
        brandButton.setBounds(50, 150, 90, 50);

        // Will display cars containing the brand the user enters
        brandButton.addActionListener( e -> {

            boolean present = false;
            String carBrand = JOptionPane.showInputDialog("Enter a Car Brand:"); // Takes in brand input
            ArrayList<Car> targetCars = new ArrayList<>();

            // Compares the brand of cars with the input, if it's the same, add car to the targetCars list and set present to true.
            for (Car C : Main.carList) {
                if (C.getBrand().equalsIgnoreCase(carBrand)) {
                    targetCars.add(C);
                    present = true;
                }
            }

            // Display carInformation of the target cars
            if (present) displayCarInformation(targetCars.toArray(new Car[0]));

            // If none is found, display error message
            else JOptionPane.showMessageDialog(null, "There are no Cars with the brand " + carBrand);

        });

        // Year Button
        JButton yearButton = new JButton("Year");
        yearButton.setBounds(170, 150, 90, 50);

        // Will display all car having the sam year as the input of the user
        yearButton.addActionListener( e -> {

            boolean present = false;
            String carYear = JOptionPane.showInputDialog("Enter a Year:"); // Year input
            ArrayList<Car> targetCars = new ArrayList<>();

            for (Car C : Main.carList) {
                if (String.valueOf(C.getYear()).equalsIgnoreCase(carYear)) { // Checks if the input is the same as the feature
                    targetCars.add(C); // If yes, add to the target cars list
                    present = true;
                }
            }

            // Displays car Information if present
            if (present) displayCarInformation(targetCars.toArray(new Car[0]));

            // Provides error message if not
            else JOptionPane.showMessageDialog(null, "There are no Cars manufactured in the year " + carYear);

        });

        // Price button
        JButton priceButton = new JButton("Price");
        priceButton.setBounds(290, 150, 90, 50);

        // Displays all cars having less price than the number entered by the user
        priceButton.addActionListener( e-> {

            boolean present = false;

            // Price input
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter a price ($) to show cars with a price less than or equal to the number"));
            ArrayList<Car> targetCars = new ArrayList<>();

            // Goes through each car and compare the price
            for (Car C : Main.carList){
                if (Double.parseDouble(C.getPricePerDay().substring(1)) <= price) { // Substring to remove the $ sign
                    targetCars.add(C);
                    present = true;
                }
            }

            // Displays information if present
            if (present) displayCarInformation(targetCars.toArray(new Car[0]));

            // Displays error message if not
            else JOptionPane.showMessageDialog(null, "There are no Cars within the price range of: $" + price);

        });

        // Mileage button
        JButton mileageButton = new JButton("Mileage");
        mileageButton.setBounds(410, 150, 90, 50);

        // Displays cars with less mileage than the number the user entered
        mileageButton.addActionListener( e-> {

            boolean present = false;

            // Mileage input
            double mileage = Double.parseDouble(JOptionPane.showInputDialog("Enter a distance (KM) to show cars with mileage less than or equal to the number"));
            ArrayList<Car> targetCars = new ArrayList<>();

            // Goes through each car in the list
            for (Car C : Main.carList){
                if (Double.parseDouble(C.getMileage().substring(0, C.getMileage().length() - 3)) <= mileage) { // Substring to remove the KM unit, and see if the value is less than the input
                    targetCars.add(C); // Add to target cars
                    present = true;
                }
            }

            if (present) displayCarInformation(targetCars.toArray(new Car[0])); // Displays car information if present

            // Displays error message if no car follows the criteria
            else JOptionPane.showMessageDialog(null, "There are no Cars within a mileage less than " + mileage);

        });

        // Add all the buttons into the panel, and the panel to the frame
        panel.add(returnButton); panel.add(searchHeader); panel.add(brandButton); panel.add(yearButton); panel.add(priceButton); panel.add(mileageButton);
        frame.add(panel);
    };

    // Method that executes the compareCarsPage
    public static void compareCarsPage(Customer customer){

        // Panel and frame initialization
        Frame frame = new Frame();
        Panel panel = new Panel(390, 150, 500, 300);

        // Return button that redirects the user back to the user's action page
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> User.actionPage(customer));

        Label compareHeader = new Label("Compare Cars", 0, 0, 500, 100, 20, true); // Header
        panel.add(returnButton); panel.add(compareHeader);

        // Car labels
        Label car1 = new Label("Car 1:", 70, 115, 100, 30, 15, false);
        Label car2 = new Label("Car 2:", 300, 115, 100, 30, 15, false);

        String[] carIDs = new String[Main.carList.size()]; // Represents the car in the combo box

        int i = 0;

        // Populating the array for the combo box
        for (Car C : Main.carList){
            carIDs[i] = C.getID() + "/" + C.getBrand() + " " + C.getModel();
            i ++;
        }

        // Initialization of combo boxes
        JComboBox carChoice1 = new JComboBox(carIDs);
        JComboBox carChoice2 = new JComboBox(carIDs);

        // Set location for combo boxes
        carChoice1.setBounds(70, 150,150, 30);
        carChoice2.setBounds(300, 150, 150, 30);

        // Compare button
        JButton compareButton = new JButton("Compare");
        compareButton.setBounds(205, 200, 90, 30);

        // e is a lambda expression that represents an Action Listener
        compareButton.addActionListener(e -> {

                Car carNo1 = null;
                Car carNo2 = null;

                // Get selected cars
                String carName1 = String.valueOf(carChoice1.getSelectedItem());
                String carName2 = String.valueOf(carChoice2.getSelectedItem());

                // Displays an error message if cars are equivalent
                if (carName1.equalsIgnoreCase(carName2)) JOptionPane.showMessageDialog(null, "Please Choose Different Cars");
                else {
                    // Get the cars
                    for (Car C : Main.carList){
                        if ((C.getID() + "/" + C.getBrand() + " " + C.getModel()).equalsIgnoreCase(carName1)) carNo1 = C;
                        if ((C.getID() + "/" + C.getBrand() + " " + C.getModel()).equalsIgnoreCase(carName2)) carNo2 = C;
                    }

                    // Show the comparison
                    showComparison(carNo1, carNo2, customer);
                    frame.dispose();
                }
        });

        // Add all elements to the panel, and the panel to the frame
        panel.add(car1); panel.add(car2); panel.add(carChoice1); panel.add(carChoice2); panel.add(compareButton);

        frame.add(panel);

    }

    // Shows comparison
    public static void showComparison(Car car1, Car car2, Customer customer){

        Frame frame = new Frame();

        Label compareHeader = new Label("Comparison", 390, 0, 500, 300, 20, true); // Header

        String[] columns = {"", "Car 1", "Car 2"}; // Column Header

        // Data in each row
        String[][] data = {
                {"Car ID", car1.getID(), car2.getID()},
                {"Car Brand", car1.getBrand(), car2.getBrand()},
                {"Car Model", car1.getModel(), car2.getModel()},
                {"Color", car1.getColor(), car2.getColor()},
                {"Year", String.valueOf(car1.getYear()), String.valueOf(car2.getYear())},
                {"Price Per Day", car1.getPricePerDay(), car2.getPricePerDay()},
                {"Mileage", car1.getMileage(), car2.getMileage()}
        };

        // Create table and initializing its properties
        JTable table = new JTable(data, columns);
        table.setBounds(396, 200, 470, 280);
        table.setRowHeight(40);
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Proceed button
        JButton proceed = new JButton("Proceed");
        proceed.setBounds(585, 510, 100, 50);

        // Redirects the user back to the compare page
        proceed.addActionListener( e -> {
            Customer.compareCarsPage(customer);
            frame.dispose();
        });

        // Add all elements to the frame
        frame.add(proceed); frame.add(compareHeader); frame.add(table);
    }

    // Rent car page
    public static void rentCarPage(Customer customer){

        // Initialization of frame and panel
        Frame frame = new Frame();
        Panel panel = new Panel(350, 50, 580, (130 + (Main.carList.size() * 30)));

        // Header
        Label title = new Label("Car ID:                         Car Name:", 100, 0, 880, 100, 15, false);

        // Return button that redirects the user back to the customer's action page
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> User.actionPage(customer));

        panel.add(returnButton);

        // Displays the information of all the cars

        int y = 50; // Initial value of the y values on the elements

        for (Car C : Main.carList){

            // Car information, with space separating to make it structured
            Label carID = new Label(C.getID(), 100, y, 850, 100, 15, false);
            Label carInformation = new Label(C.getBrand() + " " + C.getModel(), 250, y, 500, 100, 15, false);
            JButton button = new JButton("Rent"); button.setBounds(420, y + 38, 80, 25); // Add a rent button for each car

            button.addActionListener( e-> {

                // If the car is currently unavailable, will display an error message
                if (!C.isAvailable()) JOptionPane.showMessageDialog(null, "The car " + C.getBrand() + " " + C.getModel() + " is currently unavailable.");

                else {

                    int days = 0;
                    Date dateObtained = null;
                    boolean invalidDays = false;

                    // Get a valid number of rental days
                    while (!invalidDays) {

                        // Checks whether the data entered is an integer data type and greater than 0
                        try {
                            days = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of days the rental will be (Max 30 Days):"));
                            if (days > 0 && days <= 30) invalidDays = true;
                        } catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "Please Enter a valid Days Value");
                        }
                    }

                    // Get Valid Date
                    boolean validDate = false;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Create Date format
                    formatter.setLenient(false); // Ensure values in the date entered are valid
                    Date now = Date.from(Instant.now()); // Get current time

                    // Loops until date entered is valid
                    while (!validDate) {
                        try {
                            // Get date fro, user
                            String dates = JOptionPane.showInputDialog("Enter a date for the rental in the form of (dd/MM/yyyy)");
                            dateObtained = formatter.parse(dates); // Parse date

                            // Ensures that the date is after today
                            if (now.after(dateObtained)) {
                                JOptionPane.showMessageDialog(null, "Please enter a date after today");
                            } else validDate = true;

                        } catch (ParseException a) { // If there is a parse error, will display an error message and continue loop
                            JOptionPane.showMessageDialog(null, "Please enter a valid date");
                        }
                    }

                    rentCar(C, customer, days, dateObtained); // If there are no more errors, car will be rented
                    frame.dispose();
                }
            });

            panel.add(carID); panel.add(carInformation); panel.add(button); // Add elements to the panel
            y += 30; // Addition of the y value
        }

        // Adding of elements
        panel.add(title);
        frame.add(panel);

    }

    // Method that confirms the rental information
    public static void rentCar(Car car, Customer customer, int days, Date date){

        Frame frame = new Frame();

        double price = Double.parseDouble(car.getPricePerDay().substring(1)); // Get price of the car

        // Set new panel
        Panel panel = new Panel(440, 70, 440, 400);

        // Displays all necessary information regarding the rent

        Label header = new Label("Rent Information", 0, -20, 440, 200, 20, true);
        Label customerName = new Label("Customer Name: " + customer.getFirstName() + " " + customer.getLastName(), 80, 100, 300, 100, 15, false);
        Label carName = new Label("Car Name: " + car.getBrand() + " " + car.getModel(), 80, 130, 300, 100, 15, false);
        Label dateOfRent = new Label("Date of Rent: " + date, 80, 160, 300, 100, 15, false);
        Label rentPeriod = new Label("Rent Period: " + days + " days", 80, 190, 300, 100, 15, false);
        Label priceHead = new Label("Price: $" + (price * days), 80, 220, 300, 100, 15, false);

        // Cancel button that redirects the user back to the action page
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(115, 330, 90, 30);

        cancel.addActionListener( e-> {
            User.actionPage(customer);
            frame.dispose();
        });

        // Confirm button
        JButton confirm = new JButton("Confirm");
        confirm.setBounds(235, 330, 90, 30);

        // Will display that the process was successful, and make the car.available to false. Leading the user back to the actionPage.
        confirm.addActionListener( e-> {
            car.setAvailable(false);
            JOptionPane.showMessageDialog(null, "Car Rented Successfully!");
            User.actionPage(customer);
            frame.dispose();
        });

        // Addition of all elements
        panel.add(header); panel.add(customerName); panel.add(carName); panel.add(dateOfRent); panel.add(rentPeriod); panel.add(priceHead); panel.add(confirm); panel.add(cancel);
        frame.add(panel);
    }

}
