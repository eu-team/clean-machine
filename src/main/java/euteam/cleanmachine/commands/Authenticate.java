package euteam.cleanmachine.commands;

import euteam.cleanmachine.logging.MachineCommandLogger;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class Authenticate extends MachineCommand {
    public Authenticate(Machine machine, User user, MachineCommandLogger machineCommandLogger) {
        super(machine, user, "Authenticate", machineCommandLogger);
    }

    @Override
    public void execute() {
        this.machine.authenticateOnMachine(this.user.getId());
        this.log();
    }
}
