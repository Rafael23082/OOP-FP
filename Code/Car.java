final public class Car {
    private int year;
    private String brand, model, pricePerDay, mileage, color;
    final private String ID;

    private boolean available;
    // Constructor of the car class with the features of a car.
    public Car(String ID, String brand, String model, String color, int year, double pricePerDay, double mileage, boolean available){
        this.ID = ID;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.pricePerDay = "$" + pricePerDay;
        this.mileage = mileage + " KM";
        this.available = available;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getYear() {
        return year;
    }

    public String getID() {
        return ID;
    }

    public String getMileage() {
        return mileage;
    }

    public String getModel() {
        return model;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color){
        this.color = color;
    }
}
