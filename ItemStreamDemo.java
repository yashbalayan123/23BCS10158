import java.util.*;
import java.util.stream.*;

class Item {
    String title;
    double cost;
    String type;

    public Item(String title, double cost, String type) {
        this.title = title;
        this.cost = cost;
        this.type = type;
    }

    public String toString() {
        return title + " | Cost: " + cost + " | Type: " + type;
    }
}

public class ItemStreamDemo {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item("Tablet", 35000, "Electronics"),
                new Item("Camera", 45000, "Electronics"),
                new Item("Earbuds", 5000, "Electronics"),
                new Item("T-Shirt", 800, "Clothing"),
                new Item("Jacket", 3200, "Clothing"),
                new Item("Wardrobe", 40000, "Furniture"),
                new Item("Dining Table", 30000, "Furniture"),
                new Item("Air Fryer", 7000, "Appliances"),
                new Item("Coffee Maker", 5500, "Appliances")
        );

        Map<String, List<Item>> grouped = items.stream()
                .collect(Collectors.groupingBy(p -> p.type));
        System.out.println("Items grouped by type:");
        grouped.forEach((cat, list) -> System.out.println(cat + ": " + list));

        Map<String, Optional<Item>> maxPrice = items.stream()
                .collect(Collectors.groupingBy(
                        p -> p.type,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.cost))
                ));
        System.out.println("\nCostliest item in each type:");
        maxPrice.forEach((cat, prod) -> System.out.println(cat + ": " + prod.get()));

        double avgPrice = items.stream()
                .collect(Collectors.averagingDouble(p -> p.cost));
        System.out.println("\nAverage cost of all items: " + avgPrice);
    }
}
