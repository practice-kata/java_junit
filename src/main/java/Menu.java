import java.util.HashMap;
import java.util.Map;

public class Menu {

    private static Map<String, Drink> menu = new HashMap<>();

    static {
        menu.put("coke", new Drink("coke", 120));
        menu.put("tea", new Drink("tea", 150));
        menu.put("water", new Drink("water", 100));
    }

    public static Drink info(String name) {
        return menu.get(name);
    }

    public static Map<String, Drink> all() {
        return menu;
    }
}
