package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.enums.MachineType;

public class NewMachineDto {

    private String identifier;
    private MachineType machineType;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }
}
