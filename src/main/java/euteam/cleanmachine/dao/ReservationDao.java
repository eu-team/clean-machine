package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.reservation.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDao extends CrudRepository<Reservation, Long> {
}
