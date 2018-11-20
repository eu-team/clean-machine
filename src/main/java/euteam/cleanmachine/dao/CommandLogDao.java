package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.facility.CommandLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandLogDao extends CrudRepository<CommandLog, Long> {
}
