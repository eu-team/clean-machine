package euteam.cleanmachine.model.facility.decorator;

import euteam.cleanmachine.model.facility.Program;

public class ProgramDecorator extends Program {
    private Program program;

    public ProgramDecorator(Program program) {
        this.program = program;
    }

    public double getCost() {
        return program.getCost();
    }
}
