package euteam.cleanmachine.Models;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.model.enums.Powder;
import euteam.cleanmachine.model.enums.Softener;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.facility.decorator.WithPowder;
import euteam.cleanmachine.model.facility.decorator.WithSoftener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanmachineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProgramDecoratorTest {

    @Test
    public void shouldBeTheRightCost() {
        Program premiumProgram = new WithPowder(new WithSoftener(new Program(), Softener.PREMIUMSOFTENER), Powder.PREMIUMPOWDER);
        assertEquals(11, premiumProgram.getCost(), 0.0);
    }
}
