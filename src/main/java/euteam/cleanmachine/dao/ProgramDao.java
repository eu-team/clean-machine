package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.facility.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDao extends CrudRepository<Program, Long> {
}
