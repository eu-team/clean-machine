package euteam.cleanmachine.commands;

import euteam.cleanmachine.logging.MachineCommandLogger;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class SetOutOfOrder extends MachineCommand {
    public SetOutOfOrder(Machine machine, User user, MachineCommandLogger machineCommandLogger) {
        super(machine, user, "Set out of order", machineCommandLogger);
    }

    @Override
    public void execute() {
        this.machine.outOfOrder(this.user.getId());
        this.log();
    }
}
