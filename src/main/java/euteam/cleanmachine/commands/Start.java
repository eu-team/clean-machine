package euteam.cleanmachine.commands;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class Start extends MachineCommand {
    public Start() {
        super.commandName = "Start";
    }

    @Override
    public void execute(User user, Machine machine) {
        //TODO FIX command so we can add programID as parameter
        machine.startMachine(user.getId(),1);
    }
}
