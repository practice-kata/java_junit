public class VendingMachine {
    private int charge;

    public VendingMachine charge(int charge) {
        this.charge += charge;
        return this;
    }

    public Integer currentCharge() {
        return charge;
    }
}