package euteam.cleanmachine.commands;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class Idle extends MachineCommand {
    public Idle() {
        super.commandName = "Idle";
    }

    @Override
    public void execute(User user, Machine machine) {
        machine.idle();
    }
}
