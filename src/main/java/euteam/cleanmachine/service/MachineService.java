package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dto.DtoFactory;
import euteam.cleanmachine.dto.ProgramDto;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService {
    @Autowired
    private MachineDao repository;

    public List<Program> getProgramsFromMachine(Long machineID) {
        return  getMachine(machineID).getPrograms();
    }

    public Machine getMachine(Long machineID){
        return  repository.getMachineById(machineID);
    }
}
