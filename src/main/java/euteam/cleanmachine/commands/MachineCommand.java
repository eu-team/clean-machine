package euteam.cleanmachine.commands;

import euteam.cleanmachine.logging.MachineCommandLogger;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.user.User;

public abstract class MachineCommand {
    protected Machine machine;
    protected User user;
    protected String commandName;
    protected Program program;

    private MachineCommandLogger machineCommandLogger;

    protected MachineCommand(Machine machine, User user, String commandName, MachineCommandLogger machineCommandLogger) {
        this.machine = machine;
        this.user = user;
        this.commandName = commandName;
        this.machineCommandLogger = machineCommandLogger;
    }

    public abstract void execute();

    public void log() {
        this.machineCommandLogger.logMachineCommand(this);
    }

    public String getCommandName() {
        return commandName;
    }

    public Machine getMachine() {
        return machine;
    }

    public User getUser() {
        return user;
    }

    public Program getProgram() {
        return program;
    }
}
