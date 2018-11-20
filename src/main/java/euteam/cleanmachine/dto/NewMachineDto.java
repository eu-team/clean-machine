package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.enums.MachineType;

public class NewMachineDto {

    private MachineType machineType;

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }
}
