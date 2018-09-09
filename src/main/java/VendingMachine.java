import java.util.*;
import java.util.List;

public class VendingMachine {
    public Inventory inventory = new Inventory();
    private Charge charge = new Charge();
    private int dispCharge;

    public VendingMachine() {
        for(int i=0; i < 5; i++) {
            inventory.add(new Drink("coke", 120));
        }
    }

    public VendingMachine charge(Money money) {
        if (validMoney(money)) {
            this.dispCharge += money.value;
            this.charge.add(money);
        }
        return this;
    }

    private boolean validMoney(Money money) {
        List<Integer> validMoneies = Arrays.asList(10, 50, 100, 500, 1000);
        return validMoneies.contains(money.value);
    }

    public Integer dispCharge() {
        return dispCharge;
    }

    public Integer resetCharge() {
        Integer charge = this.dispCharge;
        this.dispCharge = 0;
        this.charge = new Charge();
        return charge;
    }

    public VendingMachine addDrink(Drink drink) {
        inventory.add(drink);
        return this;
    }

    public boolean canBy(String name) {
        if (inventory.size(name) == 0){
            return false;
        }
        if (dispCharge() < Menu.info(name).price) {
            return false;
        }
        return true;
    }

    public void buy(String name) {
        if (canBy(name)) {
            dispCharge = dispCharge - Menu.info(name).price;
        }
        inventory.remove(name);
    }

}
