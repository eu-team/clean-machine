package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.model.facility.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineService {
    @Autowired
    private MachineDao machineDao;

    public Machine getMachineByIdentifier(String identifier) {
        return machineDao.findByIdentifier(identifier);
    }

}
