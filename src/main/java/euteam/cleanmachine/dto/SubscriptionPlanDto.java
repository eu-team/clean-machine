package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.billing.SubscriptionPlan;
import euteam.cleanmachine.model.enums.SubscriptionPeriodicity;

public class SubscriptionPlanDto {
    private Long id;
    private String name;
    private Double price;
    private SubscriptionPeriodicity subscriptionPeriodicity;

    public SubscriptionPlanDto(SubscriptionPlan subscriptionPlan) {
        this.id = subscriptionPlan.getId();
        this.name = subscriptionPlan.getName();
        this.price = subscriptionPlan.getPrice();
        this.subscriptionPeriodicity = subscriptionPlan.getSubscriptionPeriodicity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SubscriptionPeriodicity getSubscriptionPeriodicity() {
        return subscriptionPeriodicity;
    }

    public void setSubscriptionPeriodicity(SubscriptionPeriodicity subscriptionPeriodicity) {
        this.subscriptionPeriodicity = subscriptionPeriodicity;
    }
}
