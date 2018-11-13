package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.facility.Machine;
import org.springframework.data.repository.CrudRepository;

public interface MachineDao extends CrudRepository<Machine, Long> {
    Machine findByIdentifier(String identifier);
}
