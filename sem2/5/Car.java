public class Car {
    private int year;
    private int kms;
    private String type;
    private String suspension;
    private double price;
    private String model;

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getKms() {
        return kms;
    }

    public String getSuspension() {
        return suspension;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Car() {}

    public Car(int year, int kms, String type, String suspension, double price, String model) {
        this.year = year;
        this.kms = kms;
        this.type = type;
        this.suspension = suspension;
        this.price = price;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "year=" + year +
                ", kms=" + kms +
                ", type='" + type + '\'' +
                ", suspension='" + suspension + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                '}';
    }
}

