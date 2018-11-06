package euteam.cleanmachine.model.facility;

import euteam.cleanmachine.model.billing.SubscriptionPlan;
import euteam.cleanmachine.model.facility.machine.Machine;

import javax.persistence.*;
import java.util.List;

@Entity
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    @OneToMany
    private List<SubscriptionPlan> subscriptionPlans;

    @OneToMany
    private List<Machine> machines;
}
