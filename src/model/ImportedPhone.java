package model;

public class ImportedPhone extends Phone {
    private String origin;
    private String status;


    public ImportedPhone(String name, double price, int quantity, String brand, String origin, String status) {
        super(name, price, quantity, brand);
        this.origin = origin;
        this.status = status;
    }

    public ImportedPhone(int id, String name, double price, int quantity, String brand, String origin, String status) {
        super(id, name, price, quantity, brand);
        this.origin = origin;
        this.status = status;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toCSV() {
        return getId() + "," + getName() + "," + getPrice() + "," + getQuantity() + "," + getBrand() + "," + getOrigin() + "," + getStatus() + "\n";
    }

    @Override
    public String toString() {
        return "ImportedPhone{" +
                super.toString() +
                "origin='" + origin + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
