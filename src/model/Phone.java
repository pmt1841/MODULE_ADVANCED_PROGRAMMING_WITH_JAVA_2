package model;

public abstract class Phone {
    private static int autoId = 0;
    private final int id;
    private String name;
    private double price;

    private int quantity;
    private String brand;

    public Phone(String name, double price, int quantity, String brand) {
        this.id = autoId++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
    }

    public Phone(int id, String name, double price, int quantity, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;

        if (autoId <= id) autoId = id + 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public abstract String toCSV();

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", brand='" + brand + '\'';
    }
}
