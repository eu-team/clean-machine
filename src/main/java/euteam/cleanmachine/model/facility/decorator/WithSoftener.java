package euteam.cleanmachine.model.facility.decorator;

import euteam.cleanmachine.model.enums.Softener;
import euteam.cleanmachine.model.facility.Program;

public class WithSoftener extends ProgramDecorator {

    private Softener softener;

    public WithSoftener(Program program, Softener softener) {
        super(program);
        this.softener = softener;
    }

    public double getCost() {
        return super.getCost() + this.softener.getPrice();
    }
}
