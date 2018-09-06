import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    public Map<String, Integer> drinks = new HashMap<>();
    private int charge;

    public VendingMachine(){
        drinks.put("1",0);
        drinks.put("2",0);
        drinks.put("3",0);
        drinks.put("4",0);
        drinks.put("5",0);
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
