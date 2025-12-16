package model;

public class GenuinePhone extends Phone {
    private String warrantyScope;
    private int warrantyPeriod;

    public GenuinePhone(String name, double price, int quantity, String brand, String warrantyScope, int warrantyPeriod) {
        super(name, price, quantity, brand);
        this.warrantyScope = warrantyScope;
        this.warrantyPeriod = warrantyPeriod;
    }

    public GenuinePhone(int id, String name, double price, int quantity, String brand, String warrantyScope, int warrantyPeriod) {
        super(id, name, price, quantity, brand);
        this.warrantyScope = warrantyScope;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyScope() {
        return warrantyScope;
    }

    public void setWarrantyScope(String warrantyScope) {
        this.warrantyScope = warrantyScope;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toCSV() {
        return getId() + "," + getName() + "," + getPrice() + "," + getQuantity() + "," + getBrand() + "," + getWarrantyScope() + "," + getWarrantyPeriod() + "\n";
    }

    @Override
    public String toString() {
        return "GenuinePhone{" +
                super.toString() +
                "warrantyScope='" + warrantyScope + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
                '}';
    }
}
