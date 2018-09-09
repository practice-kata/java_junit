import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VendingMachineTest {

    @Test
    void chargeValidMoney() {
        for (Integer charge: Arrays.asList(10,50,500,1000)) {
            assertThat(new VendingMachine().charge(new Money(charge)).dispCharge(), is(charge));
        }
    }

    @Test
    void chargeInvalidMoney() {
        for (Integer charge : Arrays.asList(1,5,5000,10000)) {
            assertThat(new VendingMachine().charge(new Money(charge)).dispCharge(), is(0));
        }
    }

    @Test
    void multiCharge() {
        Integer charge = new VendingMachine().charge(new Money(100)).charge(new Money(1000)).dispCharge();
        assertThat(charge, is(1100));
    }

    @Test
    void resetCharge() {
        VendingMachine vm = new VendingMachine().charge(new Money(100)).charge(new Money(1000));
        assertThat(vm.resetCharge(), is(1100));
        assertThat(vm.dispCharge(), is(0));
    }

    @Test
    void has5CokeInit() {
        VendingMachine vm= new VendingMachine();
        assertThat(vm.inventory.drinks.size(), is(5));
        vm.inventory.drinks.forEach((drink)-> {
            assertThat(drink.name, is("coke"));
            assertThat(drink.price, is(120));
        });

    }

    @Test
    void addDrink() {
        VendingMachine vm = new VendingMachine().addDrink(new Drink("water", 100));
        assertThat(vm.inventory.drinks.size(), is(6));
        assertThat(vm.inventory.drinks.get(5).name, is("water"));
        assertThat(vm.inventory.drinks.get(5).price, is(100));
    }

    @Test
    void inventory() {
        VendingMachine vendingMachine = new VendingMachine();
        assertThat(vendingMachine.inventory.toS(), is("coke 120yen: 5"));
        vendingMachine.addDrink(new Drink("coke", 120));
        assertThat(vendingMachine.inventory.toS(), is("coke 120yen: 6"));
        vendingMachine.addDrink(new Drink("water", 100));
        assertThat(vendingMachine.inventory.toS(), is("coke 120yen: 6\nwater 100yen: 1"));
    }


    @Nested
    public class canBuy {
        private VendingMachine vendingMachine;
        @BeforeEach
        void before() {
            vendingMachine = new VendingMachine();
        }
        @Test
        void lessChargeEnoughInventory() {
            vendingMachine.addDrink(new Drink("tea", 150));
            vendingMachine.charge(new Money(100)).charge(new Money(10));
            assertThat(vendingMachine.canBy("tea"), is(false));
            assertThat(vendingMachine.canBy("coke"), is(false));
        }
        @Test
        void enoughChargeAndInventory() {
            vendingMachine.charge(new Money(100)).charge(new Money(10)).charge(new Money(10));
            assertThat(vendingMachine.canBy("coke"), is(true));
        }
        @Test
        void lessInventoryEnoughtCharge() {
            vendingMachine.charge(new Money(100)).charge(new Money(50));
            assertThat(vendingMachine.canBy("tea"), is(false));
        }
    }

    @Nested
    public class buy {

        private VendingMachine vm;

        @BeforeEach
        void before() {
            vm = new VendingMachine();
            vm.addDrink(new Drink("tea", 150));
            vm.charge(new Money(500));
        }
        @Test
        void updateCharge(){
            List<String[]> patterns = Arrays.asList(
                    new String[]{"coke", "380"},
                    new String[]{"tea",  "230"}
            );
            for (String[] pattern : patterns) {
                vm.buy(pattern[0]);
                assertThat(vm.dispCharge(), is(Integer.valueOf(pattern[1])));
            }
        }
        @Test
        void updateInventory() {
            vm.buy("tea");
            assertThat(vm.inventory.toS(), is("coke 120yen: 5"));
        }
        @Test
        void cannotBuyWhenNoInventory() {
            vm.buy("tea");
            vm.buy("tea");
            assertThat(vm.dispCharge(), is(350));
            assertThat(vm.inventory.toS(), is("coke 120yen: 5"));
        }
    }

}


