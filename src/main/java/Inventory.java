import java.util.*;

public class Inventory {
    public List<Drink> drinks = new ArrayList<>();

    public int size(String name) {
        int size = 0;
        for (Drink drink : drinks) {
            if(name == drink.name) {
                size++;
            }
        }
        return size;
    }

    public void add(Drink drink) {
        drinks.add(drink);
    }

    public String toS() {
        List<String> result = new ArrayList<>();
        Menu.all().forEach((name, drink) ->{
            int size = size(drink.name);
            if(size != 0){
                result.add(drink.toS() + ": " + size);
            }
        });
        return String.join("\n", result);
    }

    void remove(String name) {
        Iterator<Drink> i = drinks.iterator();
        while(i.hasNext()){
            if (i.next().name == name){
                i.remove();
                break;
            }
        }
    }
}
