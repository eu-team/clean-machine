package euteam.cleanmachine.model.enums;

public enum Softener {
    BUDGETSOFTENER("Budget softener", 1), REGULARSOFTENER("Regular softener", 2), PREMIUMSOFTENER("Premium softener", 4);

    private double price;
    private String name;

    Softener(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
