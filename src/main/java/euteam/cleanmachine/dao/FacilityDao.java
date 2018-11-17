package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.facility.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityDao extends CrudRepository<Facility, Long> {

}
