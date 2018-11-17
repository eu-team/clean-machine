package euteam.cleanmachine.commands;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class Reopen extends MachineCommand {
    public Reopen() {
        super.commandName = "Reopen";
    }

    @Override
    public void execute(User user, Machine machine) {
        machine.unlockMachine(user.getId());
    }
}
