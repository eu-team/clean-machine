package euteam.cleanmachine.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AccountSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private SubscriptionPlan subscriptionPlans;

    private Date startDate;

    private Date endDate;
}
