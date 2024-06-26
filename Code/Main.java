import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

final public class Main implements InterfaceClass{

    // Stores all Customers
    static ArrayList<Customer> customerList = new ArrayList<>();

    // Stores all Employees
    static ArrayList<Employee> employeeList = new ArrayList<>();
    // Stores Users, and Customers as a key value pair. Used in the login feature.
    static Hashtable<User, Customer> customerHashtable = new Hashtable<>();
    // Stores Users and Employees.
    static Hashtable<User, Employee> employeeHashtable = new Hashtable<>();
    // Stores all Cars
    static ArrayList<Car> carList = new ArrayList<>();

    public static void main(String[] args){

        // Initialize default cars available
        Car car1 = new Car("C11", "Toyota", "Camry", "White", 2021, 50.0, 106 ,true);
        Car car2 = new Car("C42", "Honda", "Accord", "Black", 2020, 48.0, 240, true);
        Car car3 = new Car("C25", "Nissan", "Altima", "Silver", 2021, 50.0, 553, true);
        Car car4 = new Car("C11", "Toyota", "Corolla", "Silver", 2020, 40.0, 643, false);
        Car car5 = new Car("C41", "Toyota", "RAV4", "Blue", 2021, 60.0, 250, true);
        Car car6 = new Car("C22", "Honda", "Civic", "White", 2019, 42.0, 100, true);
        Car car7 = new Car("C133", "Honda", "CRV", "Black", 2021, 58.0, 154, true);
        Car car8 = new Car("C24", "Nissan", "Sentra", "Gray", 2020, 38.0, 234, true);
        Car car9 = new Car("C45", "Nissan", "Rogue", "Silver", 2021, 55.0, 523,true);
        Car car10 = new Car("C216", "Toyota", "Highlander", "Gray", 2022, 70.0, 12,true);

        // Add Cars
        carList.addAll(Arrays.asList(car1, car2, car3, car4, car5, car6, car7, car8, car9, car10));

        // New instance
        Main main = new Main();

        // Run homepage
        main.run();
    }

    public void run(){
        User.runHomePage();
    }
}
