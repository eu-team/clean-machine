package euteam.cleanmachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import euteam.cleanmachine.model.billing.SubscriptionPlan;
import euteam.cleanmachine.model.facility.Facility;
import euteam.cleanmachine.model.facility.Machine;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityDto {

    private Long id;
    private String name;
    private String address;
    private List<SubscriptionPlan> subscriptionPlans;
    private List<Machine> machines;

    public FacilityDto(Facility facility) {
        this.id = facility.getId();
        this.name = facility.getName();
        this.address = facility.getAddress();
        this.machines = facility.getMachines();
        this.subscriptionPlans = facility.getSubscriptionPlans();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<SubscriptionPlan> getSubscriptionPlans() {
        return subscriptionPlans;
    }

    public void setSubscriptionPlans(List<SubscriptionPlan> subscriptionPlans) {
        this.subscriptionPlans = subscriptionPlans;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
}
