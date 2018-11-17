package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dto.DtoFactory;
import euteam.cleanmachine.dto.ProgramDto;
import euteam.cleanmachine.dto.NewMachineDto;
import euteam.cleanmachine.model.facility.Dryer;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.facility.WashingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;

@Service
public class MachineService {
    @Autowired
    private MachineDao machineDao;

    public List<Program> getProgramsFromMachine(Long machineID) {
        return  getMachine(machineID).getPrograms();
    }

    public Machine getMachine(Long machineID){
        Machine m = machineDao.getMachineById(machineID);
        m.checkIfRunningStateIsOver(null);
        return m;
    }
    public Machine getMachineByIdentifier(String identifier) {
        Machine m = machineDao.findByIdentifier(identifier);
        m.checkIfRunningStateIsOver(null);
        return m;
    }

    public Machine createMachine(NewMachineDto newMachineDto) {
        Machine machine;
        switch (newMachineDto.getMachineType()) {
            case DRYER:
                machine = new Dryer(newMachineDto.getIdentifier());
                break;
            case WASHINGMACHINE:
                machine = new WashingMachine(newMachineDto.getIdentifier());
                break;
            default:
                return null;
        }

        return machineDao.save(machine);
    }
    public void update(Machine machine) {
        machineDao.save(machine);
    }
}
