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
}
