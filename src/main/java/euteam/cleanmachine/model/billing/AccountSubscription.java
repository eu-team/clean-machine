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

    public Long getId() {
        return id;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
