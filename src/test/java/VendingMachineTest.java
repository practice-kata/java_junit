import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VendingMachineTest {

    @Test
    public void charge() {
        Integer charge = new VendingMachine().charge(100).currentCharge();
        assertThat(charge, is(100));
        Integer charge2 = new VendingMachine().charge(200).currentCharge();
        assertThat(charge2, is(200));
    }
}
