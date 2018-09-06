import java.util.*;

public class VendingMachine {
    public List<List<String>> drinks = new ArrayList<>();
    private int charge;

    public VendingMachine(){
        drinks.add(Arrays.asList("coke", "120"));
        drinks.add(Arrays.asList("coke", "120"));
        drinks.add(Arrays.asList("coke", "120"));
        drinks.add(Arrays.asList("coke", "120"));
        drinks.add(Arrays.asList("coke", "120"));
    }

    public VendingMachine charge(int money) {
        if (money == 10 || money == 50 || money == 100 || money == 500 || money == 1000) {
            this.charge += money;
        }
        return this;
    }

    public Integer currentCharge() {
        return charge;
    }

    public Integer resetCharge() {
        Integer charge = this.charge;
        this.charge = 0;
        return charge;
    }
}
