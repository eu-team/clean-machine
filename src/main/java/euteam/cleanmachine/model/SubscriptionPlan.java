package euteam.cleanmachine.model;

import euteam.cleanmachine.model.enums.SubscriptionPeriodicity;

import javax.persistence.*;

@Entity
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    private SubscriptionPeriodicity subscriptionPeriodicity;
}
