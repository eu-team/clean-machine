package euteam.cleanmachine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import euteam.cleanmachine.model.enums.MachineType;
import euteam.cleanmachine.model.facility.Dryer;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.facility.WashingMachine;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MachineDto {
    private String id;
    private String identifier;
    private List<Program> programs;
    private String state;
    private MachineType machineType;

    public MachineDto(Machine machine) {

        this.id = machine.getIdentifier();
        this.identifier = machine.getIdentifier();
        this.programs =machine.getPrograms();
        this.state = machine.getState().getName();

        if (machine instanceof Dryer) {
            this.machineType = MachineType.DRYER;
        } else if (machine instanceof WashingMachine) {
            this.machineType = MachineType.WASHINGMACHINE;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }
}
