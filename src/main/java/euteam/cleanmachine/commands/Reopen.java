package euteam.cleanmachine.commands;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class Reopen extends MachineCommand {
    @Override
    public void execute(User user, Machine machine) {
        machine.unlockMachine(user.getId());
    }
}
