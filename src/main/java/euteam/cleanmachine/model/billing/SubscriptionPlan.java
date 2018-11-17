package euteam.cleanmachine.model.billing;

import euteam.cleanmachine.model.enums.SubscriptionPeriodicity;

import javax.persistence.*;

@Entity
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private double price;

    @Enumerated(EnumType.STRING)
    private SubscriptionPeriodicity subscriptionPeriodicity;

    public Long getId() {
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

    public SubscriptionPeriodicity getSubscriptionPeriodicity() {
        return subscriptionPeriodicity;
    }

    public void setSubscriptionPeriodicity(SubscriptionPeriodicity subscriptionPeriodicity) {
        this.subscriptionPeriodicity = subscriptionPeriodicity;
    }
}
