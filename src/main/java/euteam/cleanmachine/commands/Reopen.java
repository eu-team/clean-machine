package euteam.cleanmachine.commands;

import euteam.cleanmachine.logging.MachineCommandLogger;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class Reopen extends MachineCommand {
    public Reopen(Machine machine, User user, MachineCommandLogger machineCommandLogger) {
        super(machine, user, "Reopen", machineCommandLogger);
    }

    @Override
    public void execute() {
        this.machine.unlockMachine(this.user.getId());
        this.log();
    }
}