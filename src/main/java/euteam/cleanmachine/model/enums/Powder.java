package euteam.cleanmachine.model.enums;

public enum Powder {
    BUDGETPOWDER("Budget powder", 1), REGULARPOWDER("Regular powder", 2), PREMIUMPOWDER("Premium power", 4);

    private double price;
    private String name;

    Powder(String name, double price) {
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
