package euteam.cleanmachine.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class SubscriptionPayment extends Payment {
    @ManyToOne
    private AccountSubscription accountSubscription;
}
