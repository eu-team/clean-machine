package euteam.cleanmachine.model.facility.decorator;

import euteam.cleanmachine.model.enums.Powder;
import euteam.cleanmachine.model.facility.Program;

public class WithPowder extends ProgramDecorator {

    private Powder powder;

    public WithPowder(Program program, Powder powder) {
        super(program);
        this.powder = powder;
    }

    public double getCost() {
        return super.getCost() + this.powder.getPrice();
    }
}
