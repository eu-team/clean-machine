package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dto.DtoFactory;
import euteam.cleanmachine.dto.ProgramDto;
import euteam.cleanmachine.dto.NewMachineDto;
import euteam.cleanmachine.exceptions.StateTransitionException;
import euteam.cleanmachine.model.billing.OneTimePayment;
import euteam.cleanmachine.model.facility.Dryer;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.facility.WashingMachine;
import euteam.cleanmachine.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Mac;

@Service
public class MachineService {
    @Autowired
    private MachineDao machineDao;

    @Autowired
    private UserService userService;

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

    public boolean authenticateOnMachine(Long authItemToken, String machineID) {
        Machine machine = getMachineByIdentifier(machineID);
        try {
            User u = userService.getUserByAuthId(authItemToken);
            if(u==null)return false;
            machine.authenticateOnMachine(u.getId());
        }catch(StateTransitionException e){
            return false;
        }
        update(machine);
        return true;
    }

    public Long getLoggedInUserId(String machineID) {
        return getMachineByIdentifier(machineID).getLoggedInUserId();
    }

    public boolean machinelogout(String machineID) {
        Machine m =getMachineByIdentifier(machineID);
        boolean status = getMachineByIdentifier(machineID).idle();
        update(m);
        return status;
    }

    public boolean containsProgram(String machineID, long programId) {
        return getMachineByIdentifier(machineID).containsProgram(programId);
    }

    public boolean startProgram(String machineID, long programId) {
        Machine m = getMachineByIdentifier(machineID);
        if(m==null)return  false;
        Program p = m.getProgramById(programId);
        User u = userService.getUserByID(m.getLoggedInUserId());
        if(u==null)return false;
        if (u.getAccount().getBalance() < p.getCost()) return  false;
        u.getAccount().substractBalance(p.getCost());
        u.getAccount().addPayment(new OneTimePayment(p.getCost()));
        if(!m.startMachine(m.getLoggedInUserId(), programId))return false;
        userService.update(u);
        update(m);
        return  true;
    }

    public Long getProgramEndTime(String machineID) {
        return getMachineByIdentifier(machineID).getProgramEndTime();
    }

    public boolean unlockMachine(String machineID, Long authItemToken) {
        User u = userService.getUserByAuthId(authItemToken);
        if(u==null)return  false;
        Machine machine = getMachineByIdentifier(machineID);
        if(machine==null)return  false;
        boolean status = machine.unlockMachine(u.getId());
        update(machine);
        return status;
    }
}
