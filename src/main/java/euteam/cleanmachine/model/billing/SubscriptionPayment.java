package euteam.cleanmachine.model.billing;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SubscriptionPayment extends Payment {
    @ManyToOne
    private AccountSubscription accountSubscription;
}
