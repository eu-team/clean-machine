package euteam.cleanmachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import euteam.cleanmachine.model.billing.AccountSubscription;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountSubscriptionDto {
    private Long id;
    private Date startDate;
    private Date endDate;
    private SubscriptionPlanDto subscriptionPlanDto;

    public AccountSubscriptionDto(AccountSubscription accountSubscription) {
        this.id = accountSubscription.getId();
        this.startDate = accountSubscription.getStartDate();
        this.endDate = accountSubscription.getEndDate();
        this.subscriptionPlanDto = new SubscriptionPlanDto(accountSubscription.getSubscriptionPlan());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SubscriptionPlanDto getSubscriptionPlanDto() {
        return subscriptionPlanDto;
    }

    public void setSubscriptionPlanDto(SubscriptionPlanDto subscriptionPlanDto) {
        this.subscriptionPlanDto = subscriptionPlanDto;
    }
}
