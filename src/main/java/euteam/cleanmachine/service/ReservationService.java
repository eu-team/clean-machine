package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dao.ReservationDao;
import euteam.cleanmachine.dto.*;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.reservation.MaintenanceReservation;
import euteam.cleanmachine.model.reservation.OneTimeReservation;
import euteam.cleanmachine.model.reservation.RepeatingReservation;
import euteam.cleanmachine.model.reservation.Reservation;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.Employee;
import euteam.cleanmachine.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    MachineDao machineDao;

    public OneTimeReservationDto createOneTimeReservation(Customer customer, ReserveOneTimeDto reserveOneTimeDto) throws ServiceException {
        Machine machine = machineDao.findById(reserveOneTimeDto.getMachineId().toString()).orElse(null);

        if(machine == null) {
            throw new ServiceException("Machine not found");
        }

        OneTimeReservation oneTimeReservation = new OneTimeReservation(customer, reserveOneTimeDto.getStartDate(), reserveOneTimeDto.getEndDate(), machine);

        return new OneTimeReservationDto(reservationDao.save(oneTimeReservation));
    }

    public MaintenanceReservationDto createMaintenanceReservation(Employee employee, ReserveMaintenanceDto reserveMaintenanceDto) throws ServiceException {
        Machine machine = machineDao.findById(reserveMaintenanceDto.getMachineId().toString()).orElse(null);

        if(machine == null) {
            throw new ServiceException("Machine not found");
        }

        MaintenanceReservation maintenanceReservation = new MaintenanceReservation(employee, machine, reserveMaintenanceDto.getStartDate(), reserveMaintenanceDto.getEndDate());

        return new MaintenanceReservationDto(reservationDao.save(maintenanceReservation));
    }

    public RepeatingReservationDto createRepeatingReservation(User user, ReserveRepeatingDto reserveRepeatingDto) throws ServiceException {
        Machine machine = machineDao.findById(reserveRepeatingDto.getMachineId().toString()).orElse(null);

        if(machine == null) {
            throw new ServiceException("Machine not found");
        }

        RepeatingReservation repeatingReservation = new RepeatingReservation(machine, user, reserveRepeatingDto.getReservationPeriodicity(), reserveRepeatingDto.getStartDate(), reserveRepeatingDto.getEndDate());

        return new RepeatingReservationDto(reservationDao.save(repeatingReservation));
    }

    public List<Reservation> getReservationOfMachine(String machineId) throws ServiceException{
        Machine machine = machineDao.findById(machineId).orElse(null);

        if(machine == null) {
            throw new ServiceException("Machine not found");
        }
        return reservationDao.findAllByMachine(machine);
    }
}
