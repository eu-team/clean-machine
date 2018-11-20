package euteam.cleanmachine.commands;

import euteam.cleanmachine.logging.MachineCommandLogger;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.user.User;

public class Start extends MachineCommand {

    public Start(Machine machine, User user, Program program, MachineCommandLogger machineCommandLogger) {
        super(machine, user, "Start", machineCommandLogger);
        this.program = program;
    }

    @Override
    public void execute() {
        this.machine.startMachine(this.user.getId(),this.program.getId());
        this.log();
    }
}
