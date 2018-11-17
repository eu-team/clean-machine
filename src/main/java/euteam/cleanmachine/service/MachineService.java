package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dto.NewMachineDto;
import euteam.cleanmachine.model.facility.Dryer;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.WashingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;

@Service
public class MachineService {
    @Autowired
    private MachineDao machineDao;

    public Machine getMachineByIdentifier(String identifier) {
        return machineDao.findByIdentifier(identifier);
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
}
