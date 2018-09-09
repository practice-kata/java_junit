public class Drink {
    public String name;
    public Integer price;

    public Drink(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    String toS() {
        return name + " " + price + "yen";
    }
}
