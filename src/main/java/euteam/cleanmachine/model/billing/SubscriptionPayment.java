package euteam.cleanmachine.model.billing;

import euteam.cleanmachine.model.billing.Payment;
import euteam.cleanmachine.model.user.AccountSubscription;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SubscriptionPayment extends Payment {
    @ManyToOne
    private AccountSubscription accountSubscription;
}
