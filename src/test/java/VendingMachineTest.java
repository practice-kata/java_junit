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

    @Test
    public void multiCharge() {
        Integer charge = new VendingMachine().charge(100).charge(1000).currentCharge();
        assertThat(charge, is(1100));
    }

    @Test
    public void resetCharge() {
        VendingMachine vm = new VendingMachine();
        Integer change = vm.charge(100).charge(1000).resetCharge();
        assertThat(change, is(1100));
        assertThat(vm.currentCharge(), is(0));
    }
}
