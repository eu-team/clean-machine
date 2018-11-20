package euteam.cleanmachine.logging;

import euteam.cleanmachine.commands.MachineCommand;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;
import org.springframework.stereotype.Service;

@Service
public interface MachineCommandLogger {
    void logMachineCommand(MachineCommand machineCommand);
}
