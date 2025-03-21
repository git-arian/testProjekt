import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> pizzas;
    private ArrayList<Integer> quantities;
    private static int orderCounter = 1;
    private int orderID;
    private LocalDateTime orderTime;
    private LocalDateTime pickupTime;

    public Order(LocalDateTime orderTime, LocalDateTime pickupTime) {
        this.pizzas = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.orderID = orderCounter++;
        this.orderTime = orderTime;
        this.pickupTime = pickupTime;
    }

    public void addPizza(Pizza pizza, int quantity) {
        pizzas.add(pizza);
        quantities.add(quantity);
    }

    public double totalPrice() {
        double total = 0;
        for (int i = 0; i < pizzas.size(); i++) {
            total += pizzas.get(i).getPizzaPrice() * quantities.get(i);
        }
        return total;
    }

    @Override
    public String toString() {
        String result = "ORDER ID: #" + orderID + "\n" +
                "Order Date: " + orderTime.getDayOfMonth() + "-" + orderTime.getMonthValue() + "-" + orderTime.getYear() + "\n" +
                "Order Time: " + String.format("%02d:%02d", orderTime.getHour(), orderTime.getMinute()) + "\n" +
                "Pickup Time: " + String.format("%02d:%02d", pickupTime.getHour(), pickupTime.getMinute()) + "\n" +
                "Items:\n";

        for (int i = 0; i < pizzas.size(); i++) {
            result += "  - " + quantities.get(i) + "x " +
                    pizzas.get(i).getPizzaName() + " (" +
                    pizzas.get(i).getPizzaPrice() + ",- each)\n";
        }

        return result + "Total Price: " + totalPrice() + ",-\n";
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public int getOrderID() {
        return orderID;
    }
}