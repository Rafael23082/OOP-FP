import javax.swing.*;
import java.awt.*;

public class User{

    // User constructor with necessary information to log in
    final private String firstName, lastName, password; // Set to final because it will not change
    public User(String firstName, String lastName, String password){
        this.firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase(); // Capitalizes the first alphabet
        this.lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        this.password = password;
    }

    // Executes the homepage
    public static void runHomePage() {

        Frame homePage = new Frame(); // Set frame and title
        homePage.setTitle("Homepage");

        // Sets sign up button with an action listener to execute the signUpAs method when clicked
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener( e -> {
            signUpAs();
            homePage.dispose(); // Closes homepage frame
        });

        // Sets log in button with an action listener to execute the logIn method when clicked
        JButton logInButton = new JButton("Log In");
        logInButton.addActionListener( e-> {
            logIn();
            homePage.dispose();
        });

        // Sets coordinates and size of buttons
        signUpButton.setBounds(60, 500, 100, 30);
        logInButton.setBounds(170, 500, 100, 30);

        // Set a coloured section (design)
        Label background = new Label(null, 0, 0, 1280, 60, 15, false);
        background.setBackground(new Color(211, 211, 211));
        background.setOpaque(true);

        // Set Labels
        Label header = new Label("FleetFlex", 20, 0, 1280, 60, 15, false);

        Label descriptionHeader = new Label("FLEETFLEX", 60, 200, 1280, 100, 40, false);
        Label description = new Label("Welcome to FleetFlex, a modern car rental shop that provides various types of", 60, 280, 600, 100, 15, false);
        Label description2 = new Label("car. Our mission is to provide an unforgettable car rental experience, bringing joy", 60, 300, 620, 100, 15, false);
        Label description3 = new Label("towards our customers.", 60, 320, 620, 100, 15, false);

        Label signup = new Label("Join Us!", 60, 420, 620, 100, 20, false);

        // Another Design Section
        Label background2 = new Label(null, 0, 620, 1280, 80, 15, false);
        background2.setBackground(new Color(211, 211, 211));
        background2.setOpaque(true);

        Label mail = new Label("Mail: Fleetflex@yahoo.co.id", 30, 605, 1000, 100, 15, false);

        // Addition of all elements to the frame
        homePage.add(header); homePage.add(descriptionHeader); homePage.add(description); homePage.add(description2); homePage.add(description3); homePage.add(signUpButton); homePage.add(mail); homePage.add(background); homePage.add(background2); homePage.add(signup); homePage.add(logInButton);
    }

    // Method that executes the login Page when the login button is clicked
    public static void logIn(){

        // Create frame and panel, and set its coordinates and size
        Frame frame = new Frame();
        Panel panel = new Panel(330, 120, 650, 400);

        // Makes a return button that goes back to the homepage when it is clicked
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> {
            runHomePage();
            frame.dispose();
        });
        panel.add(returnButton);

        frame.setTitle("Customer Actions"); // Set Title

        // The setting of the login form, with the headers and text fields and make it centered
        Label signUpTitle = new Label("Let's Log In!", 0, 0, 700, 200, 20, true);
        panel.add(signUpTitle);

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setBounds(290, 170,200,25); // Set text fields
        panel.add(firstNameTextField);

        Label firstName = new Label("First Name:", 190, -20, 220, 400, 15, false);
        panel.add(firstName);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setBounds(290, 210,200,25); // Sets text field
        panel.add(lastNameTextField);

        Label lastName = new Label("Last Name:", 190, 20, 220, 400, 15, false);
        panel.add(lastName);

        JPasswordField passwordField = new JPasswordField(); // Set password field that conceals the contents
        passwordField.setBounds(290, 250,200,25);
        panel.add(passwordField);

        Label password = new Label("Password:", 190, 60, 220, 400, 15, false); // Sets password field
        panel.add(password);

        // Sets the login button that checks if all fields are filled, and if the user is registered
        JButton logInButton = new JButton("Log In");
        logInButton.setBounds(300, 310,100, 25);
        panel.add(logInButton);

        logInButton.addActionListener( e-> {

            // List of text fields
            final JTextField[] textFields = {firstNameTextField, lastNameTextField, passwordField};

            // Reminder that reminds the user when there are errors once everytime the button is clicked
            boolean reminder;
            reminder = fieldValidation(textFields, null);

            boolean userFound = false;

            // Checks if user is registered after all the text fields are filled.
            if (!reminder) {

                // Create User from the information obtained in the login page
                User user = new User(firstNameTextField.getText(), lastNameTextField.getText(), new String(passwordField.getPassword()));

                // Goes through the users of customers
                for (User users : Main.customerHashtable.keySet()) {

                    // Checks if the user is equals to any of the registered user
                    if (user.firstName.equals(users.firstName) && user.lastName.equals(users.lastName) && user.password.equals(users.password)) {

                        userFound = true;
                        Customer customer = Main.customerHashtable.get(users); // Get the customer value from the user key
                        actionPage(customer); // Goes to the customer action page
                        frame.dispose();
                    }
                }

                // Goes through the users of employees
                for (User users : Main.employeeHashtable.keySet()) {

                    // Checks if the user is equals to any of the registered user
                    if (user.firstName.equals(users.firstName) && user.lastName.equals(users.lastName) && user.password.equals(users.password)) {

                        userFound = true;
                        Employee employee = Main.employeeHashtable.get(users); // Get the employee value from the user key
                        actionPage(employee); // Goes to the employee action page
                        frame.dispose();
                    }
                }

                // Displays error message if user is not found
                if (!userFound) JOptionPane.showMessageDialog(null, "User invalid, please check for misspellings.");
            }

        });

        frame.add(panel); // Add panel to the frame
    }

    // Method that executes the signUpAs page
    public static void signUpAs(){

        // Create frame and panel
        Frame frame = new Frame();
        Panel panel = new Panel(390, 190, 500, 300);

        // Return button that redirects the user back to the homepage
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> {
            runHomePage();
            frame.dispose();
        });

        panel.add(returnButton);

        Label signUpTitle = new Label("Sign Up As:", 0, 0, 500, 200, 20, true); // Set the header label
        panel.add(signUpTitle);

        // Create employee and customer buttons
        JButton employee = new JButton("Employee");
        JButton customer = new JButton("Customer");

        panel.add(employee); panel.add(customer);

        employee.setBounds(110, 170, 100, 30); // Set location and size
        employee.addActionListener(e -> { // Action listener that executes the signup page with the employee value as true
            signUp(true);
            frame.dispose();
        });

        customer.setBounds(290, 170, 100, 30); // Set location and size
        customer.addActionListener( e -> {
            signUp(false); // Executes the signup page with the employee value as false
            frame.dispose();
        });

        frame.add(panel); // Add panel to the frame
    }

    // Method that executes the signUp page, with a boolean value indicating whether it is an employee or customer
    public static void signUp(boolean employee) {

        // Create frame and panel
        Frame frame = new Frame();
        Panel panel = new Panel(310, 80, 650, 500);

        frame.setTitle("Customer Actions");

        // Return button that goes back to the signUpAs page
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(3, 3, 90, 30);
        returnButton.addActionListener( e -> signUpAs());

        panel.add(returnButton);

        Label signUpTitle = new Label("Let's Sign Up!", 0, 0, 700, 200, 20, true); // Sign Up Header
        panel.add(signUpTitle);

        // Initializing all required labels and text fields / password fields

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setBounds(290, 170,200,25);
        panel.add(firstNameTextField);

        Label firstName = new Label("First Name:", 190, -20, 220, 400, 15, false);
        panel.add(firstName);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setBounds(290, 210,200,25);
        panel.add(lastNameTextField);

        Label lastName = new Label("Last Name:", 190, 20, 220, 400, 15, false);
        panel.add(lastName);

        JTextField phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(290, 250,200,25);
        panel.add(phoneNumberTextField);

        Label phoneNumber = new Label("Phone Number:", 157, 60, 220, 400, 15, false);
        panel.add(phoneNumber);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(290, 290,200,25);
        panel.add(passwordField);

        Label password = new Label("Password:", 190, 100, 220, 400, 15, false);
        panel.add(password);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(290, 330,200,25);
        panel.add(confirmPasswordField);

        Label confirmPassword = new Label("Confirm Password:", 127, 140, 220, 400, 15, false);
        panel.add(confirmPassword);

        // Sets the signup button
        JButton enter = new JButton("Sign Up");
        enter.setBounds(300, 390,100, 25);

        enter.addActionListener(e -> {

            // List of all text fields
            final JTextField[] textFields = {firstNameTextField, lastNameTextField, phoneNumberTextField, passwordField, confirmPasswordField};

            boolean reminder; // To make sure a maximum of 1 reminder is shown per iteration and indicates the end of the iteration if becomes true
            reminder = fieldValidation(textFields, null);

            if (!reminder) {

                // Create character array for the phone number string
                char[] phoneNumberArray = phoneNumberTextField.getText().toCharArray();

                // Checks whether the phone number entered is in the correct format
                for (char c : phoneNumberArray) {

                    // Checks whether each position in the string is a digit
                    if (!Character.isDigit(c) && !reminder) {
                        reminder = true;
                        JOptionPane.showMessageDialog(null, "Please enter a valid phone number");
                    }
                }

                // Ensure that the length of the phone number is valid
                if (phoneNumberArray.length < 9 && !reminder) {
                    reminder = true;
                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number");
                }

                // Get the strings from the password field
                String passwords = new String(passwordField.getPassword());
                String confirmPasswordString = new String(confirmPasswordField.getPassword());

                // Compares the password and the password confirmed strings to see if they are the same
                if (!passwords.equals(confirmPasswordString) && !reminder) {
                    JOptionPane.showMessageDialog(null, "Please confirm that the Passwords are equivalent");
                    reminder = true;
                }

                // If there are no errors restricting, will create the user
                if (!reminder) {

                    // Get all necessary information from the text fields
                    String firstNameString = firstNameTextField.getText();
                    String lastNameString = lastNameTextField.getText();
                    String phoneNumberString = phoneNumberTextField.getText();
                    String passwordString = new String(passwordField.getPassword());

                    // If it is an employee, create a new user and employee
                    if (employee) {
                        Employee employee1 = new Employee(firstNameString, lastNameString, String.valueOf(phoneNumberString), passwordString);
                        User user = new User(firstNameString, lastNameString, passwordString);
                        Main.employeeList.add(employee1); // Add to the employee list
                        Main.employeeHashtable.put(user, employee1); // Add to the employee hashtable used in login process

                        actionPage(employee1); // Executes the actionPage of an employee (as the methods are overloaded with customer and employee parameter)
                        frame.dispose();

                    } else {
                        Customer customer = new Customer(firstNameString, lastNameString, String.valueOf(phoneNumberString), passwordString); // Creates new Customer
                        User user = new User(firstNameString, lastNameString, passwordString); // User
                        Main.customerList.add(customer);  // Add to the customer list
                        Main.customerHashtable.put(user, customer); // Add to the customer hashtable

                        actionPage(customer); // Executes the actionPage of a customer
                        frame.dispose();
                    }
                }
            }
        });
        panel.add(enter);
        frame.add(panel); // Add panel to the frame
    }

    // Action page for a customer
    public static void actionPage(Customer customer){

        Frame frame = new Frame();

        // Sign out button that redirects the user back to the homepage
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(3, 663, 90, 30);
        signOutButton.addActionListener( e -> {
            runHomePage();
            frame.dispose();
        });

        frame.add(signOutButton);

        // Sets welcoming message along with the customer ID
        Label welcomingMessage = new Label("Welcome " + customer.getFirstName() + " " + customer.getLastName() + "!", 20, 0, 1280, 100, 25, false);
        Label customerID = new Label("Customer ID: " + customer.customerID, 20, 35, 1280, 100, 15, false);

        frame.add(welcomingMessage);
        frame.add(customerID);

        Panel showCar = new Panel(150, 180, 300, 200); // Creates panel for the show car action

        Label showAllCarsHeader = new Label("Show All Cars", 0, 0, 300, 100, 15, true); // Header
        showCar.add(showAllCarsHeader);

        // Show Button
        JButton showAllCarsButton = new JButton("Show");
        showAllCarsButton.setBounds(110, 100, 90, 50);
        showCar.add(showAllCarsButton);

        UIManager.put("OptionPane.messagefont", new Font(Font.MONOSPACED, Font.PLAIN, 15)); // Make each letter in JOptionPane messages to occupy the same space

        // Will display car information by executing displayCarInformation method with the list of all cars as an input
        showAllCarsButton.addActionListener(e -> Customer.displayCarInformation(Main.carList.toArray(new Car[0])));

        Panel searchCars = new Panel(500, 180, 300, 200); // Panel for search cars function

        Label searchCarsHeader = new Label("Search Cars", 0, 0, 300, 100, 15, true); // Header
        searchCars.add(searchCarsHeader);

        // Search button
        JButton searchCarButton = new JButton("Search");
        searchCarButton.setBounds(110, 100, 90, 50);
        searchCars.add(searchCarButton);

        // Redirects the user to another search page
        searchCarButton.addActionListener( e -> {
            Customer.searchCarPage(customer);
            frame.dispose();
        });

        Panel compareCars = new Panel(850, 180, 300, 200); // Panel for the compare cars action

        Label compareCarsHeader = new Label("Compare Cars", 0, 0, 300, 100, 15, true); // Header
        compareCars.add(compareCarsHeader);

        // Compare button
        JButton compareCarButton = new JButton("Compare");
        compareCarButton.setBounds(110, 100, 90, 50);
        compareCars.add(compareCarButton);

        // Will redirect user to another compare page
        compareCarButton.addActionListener(e -> {
            Customer.compareCarsPage(customer);
            frame.dispose();
        });

        Panel rentCars = new Panel(500, 430, 300, 200); // Panel for the rent car action

        Label rentCarsHeader = new Label("Rent Car", 0, 0, 300, 100, 15, true); // Header
        rentCars.add(rentCarsHeader);

        // Rent button
        JButton rentCarButton = new JButton("Rent");
        rentCarButton.setBounds(110, 100, 90, 50);
        rentCars.add(rentCarButton);

        // Will redirect user to another rent page
        rentCarButton.addActionListener( e-> {
            Customer.rentCarPage(customer);
            frame.dispose();
        });

        // Add all panels to the frame
        frame.add(showCar);
        frame.add(searchCars);
        frame.add(compareCars);
        frame.add(rentCars);
    }

    // Action page for employees
    public static void actionPage(Employee employee){

        Frame frame = new Frame(); // Create frame

        // Sign out button that redirects the user back to the homepage
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(3, 663, 90, 30);
        signOutButton.addActionListener( e -> {
            runHomePage();
            frame.dispose();
        });

        frame.add(signOutButton);

        // Welcoming message and employee ID
        Label welcomingMessage = new Label("Welcome " + employee.getFirstName() + " " + employee.getLastName() + "!", 20, 0, 1280, 100, 25, false);
        Label employeeID = new Label("Employee ID: " + employee.employeeID, 20, 35, 1280, 100, 15, false);

        Panel addCar = new Panel(150, 180, 300, 200); // Panel for the add car function

        Label addCarHeader = new Label("Add Car", 0, 0, 300, 100, 15, true); // Header
        addCar.add(addCarHeader);

        // Add button
        JButton addCarsButton = new JButton("Add");
        addCarsButton.setBounds(105, 100, 90, 50);
        addCar.add(addCarsButton);

        // Redirects the user to another add car page when clicked
        addCarsButton.addActionListener( e-> {
            Employee.addCar(employee);
            frame.dispose();
        });

        Panel deleteCar = new Panel(500, 180, 300, 200); // Panel for the delete car action

        Label searchCarsHeader = new Label("Delete Car", 0, 0, 300, 100, 15, true); // Header
        deleteCar.add(searchCarsHeader);

        // Delete button
        JButton deleteCarButton = new JButton("Delete");
        deleteCarButton.setBounds(105, 100, 90, 50);
        deleteCar.add(deleteCarButton);

        // Redirect to another delete page when clicked
        deleteCarButton.addActionListener( e-> {
            Employee.deleteCar(employee);
            frame.dispose();
        });

        Panel editCars = new Panel(850, 180, 300, 200); // Panel for the edit car action

        Label editCarsHeader = new Label("Edit Car", 0, 0, 300, 100, 15, true); // Header
        editCars.add(editCarsHeader);

        // Edit button
        JButton editCarButton = new JButton("Edit");
        editCarButton.setBounds(110, 100, 90, 50);
        editCars.add(editCarButton);

        // Redirects user to another edit car page when clicked
        editCarButton.addActionListener( e-> {
            Employee.editCarPage(employee);
            frame.dispose();
        });

        Panel showCars = new Panel(500, 430, 300, 200); // Panel for the rent car action

        Label showCarsHeader = new Label("Show Cars", 0, 0, 300, 100, 15, true); // Header
        showCars.add(showCarsHeader);

        // Show Button
        JButton showCarButton = new JButton("Show");
        showCarButton.setBounds(110, 100, 90, 50);
        showCars.add(showCarButton);

        // Will display all cars information
        showCarButton.addActionListener( e-> {
            Customer.displayCarInformation(Main.carList.toArray(new Car[0]));
        });


        // Add all panels to the frame
        frame.add(welcomingMessage); frame.add(employeeID); frame.add(addCar); frame.add(deleteCar); frame.add(editCars); frame.add(showCars);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Method that verifies text fields whether they are valid. (Emptiness and Data type correctness).
    public static boolean fieldValidation(JTextField[] textFields, JTextField[] numberFields){

        boolean reminder = false;

        // Makes sure all fields are filled
        for (JTextField textField : textFields){
            if (textField.getText().isEmpty() && !reminder) {
                reminder = true;
                JOptionPane.showMessageDialog(null, "Please fill in all of the fields");
                break;
            }
        }

        // Checks whether the values are in integer for fields that require integer inputs, may be null because some validation do not require numbers
        if (numberFields != null) {
            for (JTextField numberField : numberFields) {
                try {
                    int value = Integer.parseInt(numberField.getText());
                } catch (NumberFormatException a) { // Makes sure input is in the correct data type
                    if (!reminder) JOptionPane.showMessageDialog(null, "Please enter valid values");
                    reminder = true; // Ensures reminder is displayed once
                    break;
                }
            }
        }

        return reminder; // true if invalid, false if valid
    }
}
