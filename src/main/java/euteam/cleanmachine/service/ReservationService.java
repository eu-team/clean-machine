package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dao.ReservationDao;
import euteam.cleanmachine.dto.MaintenanceReservationDto;
import euteam.cleanmachine.dto.OneTimeReservationDto;
import euteam.cleanmachine.dto.ReserveMaintenanceDto;
import euteam.cleanmachine.dto.ReserveOneTimeDto;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.reservation.MaintenanceReservation;
import euteam.cleanmachine.model.reservation.OneTimeReservation;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    MachineDao machineDao;

    public OneTimeReservationDto createOneTimeReservation(Customer customer, ReserveOneTimeDto reserveOneTimeDto) throws ServiceException {
        Machine machine = machineDao.findById(reserveOneTimeDto.getMachineId()).orElse(null);

        if(machine == null) {
            throw new ServiceException("Machine not found");
        }

        OneTimeReservation oneTimeReservation = new OneTimeReservation(customer, reserveOneTimeDto.getReservationDate(), machine);

        return new OneTimeReservationDto(reservationDao.save(oneTimeReservation));
    }

    public MaintenanceReservationDto createMaintenanceReservation(Employee employee, ReserveMaintenanceDto reserveMaintenanceDto) throws ServiceException {
        Machine machine = machineDao.findById(reserveMaintenanceDto.getMachineId()).orElse(null);

        if(machine == null) {
            throw new ServiceException("Machine not found");
        }

        MaintenanceReservation maintenanceReservation = new MaintenanceReservation(employee, machine, reserveMaintenanceDto.getStartDate(), reserveMaintenanceDto.getEndDate());

        return new MaintenanceReservationDto(reservationDao.save(maintenanceReservation));
    }
}
