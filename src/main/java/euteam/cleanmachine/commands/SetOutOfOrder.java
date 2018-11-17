package euteam.cleanmachine.commands;

import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;

public class SetOutOfOrder extends MachineCommand {
    public SetOutOfOrder() {
        super.commandName = "Set out of order";
    }

    @Override
    public void execute(User employee, Machine machine) {
        machine.outOfOrder(employee.getId());
    }
}
