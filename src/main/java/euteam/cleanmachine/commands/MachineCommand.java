package euteam.cleanmachine.commands;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public abstract class MachineCommand {
    public abstract void execute(User user, Machine machine);
}
