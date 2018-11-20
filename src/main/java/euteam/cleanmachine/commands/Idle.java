package euteam.cleanmachine.commands;

import euteam.cleanmachine.logging.MachineCommandLogger;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class Idle extends MachineCommand {
    public Idle(Machine machine, User user, MachineCommandLogger machineCommandLogger) {
        super(machine, user, "Idle", machineCommandLogger);
    }

    @Override
    public void execute() {
        this.machine.idle();
        this.log();
    }
}
