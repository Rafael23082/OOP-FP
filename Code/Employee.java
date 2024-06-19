import javax.swing.*;
import java.util.Random;

final public class Employee extends User{

    String employeeID, phoneNumber;

    // Constructor containing the user attributes, with additional attributes
    public Employee(String firstName, String lastName, String phoneNumber, String password){

        super(firstName, lastName, password);
        this.phoneNumber = phoneNumber;
        this.employeeID = "EMP" + Main.employeeList.size();

    }

    // Add Car function
    public static void addCar(Employee employee){

        Frame frame = new Frame();
        Panel panel = new Panel(330, 50, 650, 500);

        // Return button that redirects the user back to the action page
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> {
            User.actionPage(employee);
            frame.dispose();
        });

        panel.add(returnButton);

        // Label and text fields to create a new car and add it ito the car list
        Label addCarTitle = new Label("Add Car", 0, 0, 700, 200, 20, true);
        panel.add(addCarTitle);

        JTextField carBrandTextField = new JTextField();
        carBrandTextField.setBounds(290, 170,200,25);
        panel.add(carBrandTextField);

        Label carBrand = new Label("Car Brand:", 190, -20, 220, 400, 15, false);
        panel.add(carBrand);

        JTextField carModelTextField = new JTextField();
        carModelTextField.setBounds(290, 210,200,25);
        panel.add(carModelTextField);

        Label carModel = new Label("Car Model:", 190, 20, 220, 400, 15, false);
        panel.add(carModel);

        JTextField colorTextField = new JTextField();
        colorTextField.setBounds(290, 250,200,25);
        panel.add(colorTextField);

        Label color = new Label("Color:", 220, 60, 220, 400, 15, false);
        panel.add(color);

        JTextField yearTextField = new JTextField();
        yearTextField.setBounds(290, 290,200,25);
        panel.add(yearTextField);

        Label year = new Label("Year:", 220, 100, 220, 400, 15, false);
        panel.add(year);

        JTextField priceTextField = new JTextField();
        priceTextField.setBounds(290, 330,200,25);
        panel.add(priceTextField);

        Label price = new Label("Price/Hour:", 180, 140, 220, 400, 15, false);
        panel.add(price);

        JTextField mileageTextField = new JTextField();
        mileageTextField.setBounds(290, 370,200,25);
        panel.add(mileageTextField);

        Label mileage = new Label("Mileage:", 205, 180, 220, 400, 15, false);
        panel.add(mileage);

        // Add button
        JButton addButton = new JButton("Add");
        addButton.setBounds(300, 420, 90, 50);
        panel.add(addButton);

        // Creates a new car and add it to the list
        addButton.addActionListener( e -> {

            Random random = new Random(); // To create the car ID
            String randomID = null;
            boolean loop = true;

            // Make sure that the car ID is unique
            while (loop) {

                boolean duplicate = false;

                randomID = "C" + random.nextInt(0, 100);
                for (Car C : Main.carList){
                    if (C.getID().equalsIgnoreCase(randomID)) duplicate = true;
                }
                if (!duplicate) loop = false;
            }

            // Get attributes
            String brand = carBrandTextField.getText().substring(0,1).toUpperCase() + carBrandTextField.getText().substring(1).toLowerCase();
            String model = carModelTextField.getText().substring(0,1).toUpperCase() + carModelTextField.getText().substring(1).toLowerCase();
            String colour = colorTextField.getText().substring(0,1).toUpperCase() + colorTextField.getText().substring(1).toLowerCase();
            int yearValue = Integer.parseInt(yearTextField.getText());
            double pricePerDay = Double.parseDouble(priceTextField.getText());
            double mileageValue = Double.parseDouble(mileageTextField.getText());

            // Create new car and add it to the car list
            Car car = new Car(randomID, brand, model, colour, yearValue, pricePerDay, mileageValue, true);
            Main.carList.add(car);

            // Show a message stating that the car is added successfully
            JOptionPane.showMessageDialog(null, "Car " + brand + " " + model + " has been successfully added!");
        });

        frame.add(panel); // Add panel to the frame
    }


    // Delete Car Page
    public static void deleteCar(Employee employee){

        // Creates new panel and frame
        Frame frame = new Frame();
        Panel panel = new Panel(380, 150, 520, 300);

        // Return button that redirects the user back to the action page
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> {
            User.actionPage(employee);
            frame.dispose();
        });

        // Headers
        Label deleteHeader = new Label("Delete Car", 0, 0, 520, 70, 20, true);
        Label toBeDeletedCar = new Label("Car Choice", 0, 100, 520, 70, 15, true);

        // Creation of combo box
        String[] carIDs = new String[Main.carList.size()];

        int i = 0;
        for (Car C : Main.carList){
            carIDs[i] = C.getID() + "/" + C.getBrand() + " " + C.getModel();
            i ++;
        }

        // Combo box
        JComboBox carChoice = new JComboBox(carIDs);
        carChoice.setBounds(190, 160,150, 30);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(210, 220, 110, 30);

        // Deletes car if car is present
        deleteButton.addActionListener( e-> {

            // Get selected car
            String carName = String.valueOf(carChoice.getSelectedItem());

            // Goes through the car list to see if it exists
            for (Car C : Main.carList){

                // If yes, will ask for confirmation
                if ((C.getID() + "/" + C.getBrand() + " " + C.getModel()).equalsIgnoreCase(carName)) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Confirm Delete Car " + C.getBrand() + " " + C.getModel() + "?");

                    // If user confirms, will remove the car and displays a message
                    if (confirm == 0){
                        Main.carList.remove(C);
                        JOptionPane.showMessageDialog(null, "Car " + C.getBrand() + " " + C.getModel() + " is Removed!");
                        deleteCar(employee);
                        frame.dispose();

                    // Otherwise, will cancel the removal
                    } else JOptionPane.showMessageDialog(null, "Removal Canceled");
                }
            }

        });

        // Addition of elements
        panel.add(toBeDeletedCar); panel.add(deleteHeader); panel.add(carChoice); panel.add(deleteButton); panel.add(returnButton);
        frame.add(panel);
    }

    // Page where users edit car information
    public static void editCarPage(Employee employee){

        // Frame and panel initialization
        Frame frame = new Frame();
        Panel panel = new Panel(340, 200, 550, 300);

        // Return button that redirects the user back to the action page
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> {
            User.actionPage(employee);
            frame.dispose();
        });

        // Headers
        Label editHeader = new Label("Edit Car", 0, 0, 520, 70, 20, true);
        Label toBeEditedCar = new Label("Car Choice", 0, 70, 520, 70, 15, true);

        // Creation of combo Boxes
        String[] carIDs = new String[Main.carList.size()];

        int i = 0;
        for (Car C : Main.carList){
            carIDs[i] = C.getID() + "/" + C.getBrand() + " " + C.getModel();
            i ++;
        }

        // Combo box
        JComboBox carChoice = new JComboBox(carIDs);
        carChoice.setBounds(190, 130,150, 30);

        // Edit button
        JButton editButton = new JButton("Edit");
        editButton.setBounds(210, 190, 110, 30);

        // Will redirect the user to another page
        editButton.addActionListener( e-> {

            // Get selected car
            String carSelected = String.valueOf(carChoice.getSelectedItem());

            for (Car C : Main.carList){

                // Get Car
                if ((C.getID() + "/" + C.getBrand() + " " + C.getModel()).equalsIgnoreCase(carSelected)) {
                    editCar(C, employee);
                    frame.dispose();
                }
            }

        });

        // Addition of all elements
        panel.add(editHeader); panel.add(toBeEditedCar); panel.add(carChoice); panel.add(editButton); panel.add(returnButton);
        frame.add(panel);
    }

    // Edit car page that lets the user choose on what feature they want to edit
    public static void editCar(Car car, Employee employee){

        // Frame and panel initialization
        Frame frame = new Frame();
        Panel panel = new Panel(340, 200, 550, 300);

        // Return button that redirects the user back to the editCarPage
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> {
            editCarPage(employee);
            frame.dispose();
        });

        Label searchHeader = new Label("Edit Car's", 0, 0, 550, 100, 20, true); // Header

        // Buttons representing the features of the car to be edited.
        // Will execute the editFunction with the feature and the car as its parameters
        // The edit function returns the string changed by the user
        // If the string is null, it means that the user cancelled the changes, leading to no changes made

        // Brand Button
        JButton brandButton = new JButton("Brand");
        brandButton.setBounds(30, 150, 90, 50);

        brandButton.addActionListener(e->{
            String brand = editFunction("Brand", car);
            if (brand != null) car.setBrand(brand);
        });

        // Model Button
        JButton modelButton = new JButton("Model");
        modelButton.setBounds(130, 150, 90, 50);

        modelButton.addActionListener(e-> {
            String model = editFunction("Model", car);
            if (model != null) car.setModel(model);
        });

        // Year Button
        JButton yearButton = new JButton("Year");
        yearButton.setBounds(230, 150, 90, 50);

        yearButton.addActionListener(e->{
            String year = editFunction("Year", car);
            if (year != null) car.setYear(Integer.parseInt(year));
        });

        // Price Button
        JButton priceButton = new JButton("Price");
        priceButton.setBounds(330, 150, 90, 50);

        priceButton.addActionListener(e->{
            String price = editFunction("Price", car);
            if (price != null) {
                double priceDouble = Double.parseDouble(price);
                car.setPricePerDay("$" + priceDouble);
            }
        });

        // Mileage Button
        JButton mileageButton = new JButton("Mileage");
        mileageButton.setBounds(430, 150, 90, 50);

        mileageButton.addActionListener(e->{
            String mileage = editFunction("Mileage", car);
            if (mileage != null){
                double mileageDouble = Double.parseDouble(mileage);
                car.setMileage(mileageDouble + " KM");
            }
        });

        // Status Button
        JButton statusButton = new JButton("Status");
        statusButton.setBounds(180, 210, 90, 50);

        statusButton.addActionListener(e-> {
            String status = editFunction("Status", car);
            if (status.equals("Available")) car.setAvailable(true);
            else if (status.equals("Unavailable")) car.setAvailable(false);
        });

        // Color Button
        JButton colorButton = new JButton("Color");
        colorButton.setBounds(280, 210, 90, 50);

        colorButton.addActionListener(e-> {
            String color = editFunction("Color", car);
            car.setColor(color);
        });

        // Addition off all elements
        panel.add(returnButton); panel.add(searchHeader); panel.add(brandButton); panel.add(modelButton);
        panel.add(yearButton); panel.add(priceButton); panel.add(mileageButton); panel.add(statusButton); panel.add(colorButton);

        frame.add(panel);

    }

    // Function that returns the change the user made
    public static String editFunction(String feature, Car car){

        // Get the feature string
        String carFeature = switch (feature) {
            case "Brand" -> car.getBrand();
            case "Model" -> car.getModel();
            case "Year" -> String.valueOf(car.getYear());
            case "Price" -> car.getPricePerDay().substring(1);
            case "Mileage" -> car.getMileage().substring(0, car.getMileage().length() - 3);
            case "Color" -> car.getColor();
            default -> null;
        };

        // Change string format, so it is not in true or false
        if (feature.equals("Status")){
            if (car.isAvailable()) carFeature = "Available";
            else carFeature = "Unavailable";
        }

        boolean validInput = false;
        String change = null;

        while (!validInput) {
            // Get the new string
            change = JOptionPane.showInputDialog("Edit " + feature + " for Car " + car.getBrand() + " " + car.getModel() +
                    "\n\nEnter a " + feature + ":");

            // If it is status, it will check whether the input is valid or not (Available / Unavailable)
            if (feature.equalsIgnoreCase("Status")) {
                if (change.equalsIgnoreCase("Available") || change.equalsIgnoreCase("Unavailable")){
                    validInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter Available or Unavailable");
                }
            }

            // Ensure the features that require an integer input is in integer data type
            else if (feature.equalsIgnoreCase("Year") || feature.equalsIgnoreCase("Price") || feature.equalsIgnoreCase("Mileage")) {

                // Converts string to integer, if there is an error, will display an error message
                try {
                    int intValue = Integer.parseInt(change);
                    validInput = true;
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter an integer");
                }
            } else if (change == null) { // Makes sure input is not null
                JOptionPane.showMessageDialog(null, "Please enter a valid input");
            } else validInput = true; // Will not loop if it is not regarding the status
        }
        // Ask for confirmation
        int confirmation = JOptionPane.showConfirmDialog(null, "Confirm change of " + feature + " from " + carFeature + " to " + change + "?");

        // If yes, it will return the string changed and display a success message
        if (confirmation == 0) {
            JOptionPane.showMessageDialog(null, "Car " + feature + " is successfully changed from " + carFeature + " to " + change + "!");
            return change;
        }
        else return null; // If not, it will return null leading to no changes made
    }
}
