package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.reservation.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends CrudRepository<Reservation, Long> {
    List<Reservation> findAllByMachine(Machine machine);
}
