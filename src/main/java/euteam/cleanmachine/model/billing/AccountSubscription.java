package euteam.cleanmachine.model.billing;

import euteam.cleanmachine.model.billing.SubscriptionPlan;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AccountSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private SubscriptionPlan subscriptionPlan;

    private Date startDate;

    private Date endDate;
}
