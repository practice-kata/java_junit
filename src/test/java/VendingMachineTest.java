import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VendingMachineTest {

    @Test
    public void chargeValidMoney() {
        for (Integer charge: Arrays.asList(10,50,500,1000)) {
            assertThat(new VendingMachine().charge(charge).currentCharge(), is(charge));
        }
    }

    @Test
    public void chargeInvalidMoney() {
        for (Integer charge : Arrays.asList(1,5,5000,10000)) {
            assertThat(new VendingMachine().charge(charge).currentCharge(), is(0));
        }
    }

    @Test
    public void multiCharge() {
        Integer charge = new VendingMachine().charge(100).charge(1000).currentCharge();
        assertThat(charge, is(1100));
    }

    @Test
    public void resetCharge() {
        VendingMachine vm = new VendingMachine().charge(100).charge(1000);
        assertThat(vm.resetCharge(), is(1100));
        assertThat(vm.currentCharge(), is(0));
    }
}
