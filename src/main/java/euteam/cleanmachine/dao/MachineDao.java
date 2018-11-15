package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineDao extends CrudRepository<Machine,Long> {

    Machine getMachineById(Long machineID);
    Machine findByIdentifier(String identifier);

}
