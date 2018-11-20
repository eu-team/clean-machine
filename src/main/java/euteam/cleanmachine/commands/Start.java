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
        //TODO FIX command so we can add programID as parameter
        this.machine.startMachine(this.user.getId(),1);
        this.log();
    }
}
