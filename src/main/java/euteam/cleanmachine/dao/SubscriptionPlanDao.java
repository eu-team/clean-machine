package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.billing.SubscriptionPlan;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionPlanDao extends CrudRepository<SubscriptionPlan, Long> {
}
