package euteam.cleanmachine.service;

import euteam.cleanmachine.commands.Authenticate;
import euteam.cleanmachine.commands.Idle;
import euteam.cleanmachine.commands.Reopen;
import euteam.cleanmachine.commands.Start;
import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dto.DtoFactory;
import euteam.cleanmachine.dto.ProgramDto;
import euteam.cleanmachine.dto.NewMachineDto;
import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.exceptions.StateTransitionException;
import euteam.cleanmachine.logging.DatabaseLogger;
import euteam.cleanmachine.model.billing.OneTimePayment;
import euteam.cleanmachine.model.facility.Dryer;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.facility.WashingMachine;
import euteam.cleanmachine.model.reservation.Reservation;
import euteam.cleanmachine.model.facility.machine.state.AuthenticatedState;
import euteam.cleanmachine.model.facility.machine.state.LockedState;
import euteam.cleanmachine.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Mac;

@Service
public class MachineService {
    @Autowired
    private MachineDao machineDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DatabaseLogger databaseLogger;

    public List<ProgramDto> getProgramsFromMachine(String machineID) {
        List<ProgramDto> programs = new ArrayList<>();
        for (Program program: getMachineByIdentifier(machineID).getPrograms()) {
            programs.add(DtoFactory.programToDto(program));
        }
        return  programs;
    }

    public Machine getMachineByIdentifier(String identifier) {
        Machine m = machineDao.findByIdentifier(identifier);
        if(m!=null)m.checkIfRunningStateIsOver(null);
        return m;
    }

    public Machine createMachine(NewMachineDto newMachineDto) {
        Machine machine;
        switch (newMachineDto.getMachineType()) {
            case DRYER:
                machine = new Dryer();
                break;
            case WASHINGMACHINE:
                machine = new WashingMachine();
                break;
            default:
                return null;
        }

        return machineDao.save(machine);
    }

    public String getMachineStatus(String machineID){
       return  getMachineByIdentifier(machineID).getState().getName();
    }

    public void update(Machine machine) {
        machineDao.save(machine);
    }

    public UserDto authenticateOnMachine(Long authItemToken, String machineID) {
        Machine machine = machineDao.findByIdentifier(machineID);
        User user = userService.getUserByAuthId(authItemToken);

        if (user == null) {
            throw new ServiceException("Unknown user");
        }

        if (machine == null) {
            throw new ServiceException("Unknown machine");
        }

        Reservation reservation = reservationService.checkIfMachineReserved(machine, new Date());
        if(reservation != null) {
            if(reservation.getUser().getId().equals(user.getId())) {
                Authenticate command = new Authenticate(machine, user, databaseLogger);
                command.execute();
            } else {
                throw new ServiceException("Machine reserved by an other user");
            }
        } else {
            Authenticate command = new Authenticate(machine, user, databaseLogger);
            command.execute();
        }

        return new UserDto(user);
    }

    public Long getLoggedInUserId(String machineID) {
        return getMachineByIdentifier(machineID).getLoggedInUserId();
    }

    public void machinelogout(String machineID) {
        Machine machine = machineDao.findById(machineID).orElse(null);
        if (machine == null) {
            throw new ServiceException("Machine not found");
        }

        AuthenticatedState state = (AuthenticatedState)(machine.getState());
        User currentlyAuthenticatedUser = userService.getUserByID(state.getUserId());

        if (currentlyAuthenticatedUser == null) {
            throw new ServiceException("No user currently authenticated");
        }

        Idle command = new Idle(machine, currentlyAuthenticatedUser, databaseLogger);
        command.execute();
    }

    public boolean containsProgram(String machineID, long programId) {
        return getMachineByIdentifier(machineID).containsProgram(programId);
    }

    /**
     * Starts a program on the machine
     * @param machineID
     * @param programId
     * @return remaining time for program to finish
     */
    public Long startProgram(String machineID, long programId) {
        Machine machine = machineDao.findById(machineID).orElse(null);
        if (machine == null) {
            throw new ServiceException("Machine not found");
        }
        if (!(machine.getState() instanceof AuthenticatedState)) {
            throw new ServiceException("Machine cannot be started if no user is logged in");
        }

        User user = userService.getUserByID(((AuthenticatedState)(machine.getState())).getUserId());
        if (user == null) {
            throw new ServiceException("Logged user not found");
        }

        Program program = machine.getProgramById(programId);
        if (program == null) {
            throw new ServiceException("Program not found on this machine");
        }

        if (user.getAccount().getBalance() < program.getCost()) {
            throw new ServiceException("Not enough funds for this program");
        }

        user.getAccount().substractBalance(program.getCost());
        user.getAccount().addPayment(new OneTimePayment(program.getCost()));

        Start command = new Start(machine, user, program, databaseLogger);
        command.execute();

        userService.update(user);

        Long endTime = getProgramEndTime(machine.getIdentifier());
        if (endTime == null) {
            throw new ServiceException("The program is endless");
        }

        return endTime;
    }

    public Long getProgramEndTime(String machineID) {
        return getMachineByIdentifier(machineID).getProgramEndTime();
    }

    public void unlockMachine(String machineID, Long authItemToken) {
        User user = userService.getUserByAuthId(authItemToken);
        if (user == null) {
            throw new ServiceException("Unknown user");
        }

        Machine machine = machineDao.findById(machineID).orElse(null);
        if (machine == null) {
            throw new ServiceException("Machine not found");
        }

        Reopen command = new Reopen(machine, user, databaseLogger);
        command.execute();
    }
}
