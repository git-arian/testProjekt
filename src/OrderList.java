import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class OrderList {
    private ArrayList<Order> activeOrders;
    private ArrayList<Order> completedOrders;
    private OrderHistory orderHistory;
    private Scanner scanner;
    private Menu menu;

    public OrderList(Scanner scanner, Menu menu) {
        this.scanner = scanner;
        this.menu = menu;
        this.orderHistory = new OrderHistory();
        activeOrders = new ArrayList<>();
        completedOrders = new ArrayList<>();
        testOrders();
    }

    public void manageOrders() {
        boolean b = true;
        while (b) {
            System.out.println();
            System.out.println("1. View active orders");
            System.out.println("2. Mark order complete");
            System.out.println("3. View order history");
            System.out.println("4. Return to main menu");
            System.out.println("Choose (1-4):");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1 -> showActiveOrders(activeOrders);
                    case 2 -> completeOrder();
                    case 3 -> orderHistory.orderHistoryMenu();
                    case 4 -> b = false;
                    default -> System.out.println("Error: Only numbers (1-4) allowed.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Only numbers allowed!");
                scanner.nextLine();
            }
        }
    }

    private void showActiveOrders(ArrayList<Order> orders) {

        if (activeOrders.isEmpty()) {
            System.out.println("No active orders!");
            return;
        }

        orders.sort(Comparator.comparing(Order::getPickupTime));

        System.out.println("==== ACTIVE ORDERS ===");
        for (Order order : activeOrders) {
            System.out.println(order);
            System.out.println("====================");
        }
    }

    private void completeOrder() {
        System.out.println("Which order would you like to mark complete? (Enter order ID)");

        try {
            int orderID = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < activeOrders.size(); i++) {
                if (activeOrders.get(i).getOrderID() == orderID) {
                    Order orderToMove = activeOrders.remove(i);
                    orderHistory.completedOrders.add(orderToMove);
                    System.out.println("Order " + orderID + " marked complete!");
                    return;
                }
            }
                System.out.println("Order with ID " + orderID + " not found!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.nextLine();
        }
    }

    private void testOrders() {
        Pizza marios = getPizzaById(1);
        Pizza magherita = getPizzaById(4);
        Pizza bblCrust = getPizzaById(7);

        Order order1 = new Order(LocalDateTime.now().plusMinutes(13), LocalDateTime.now().plusHours(1));
        order1.addPizza(marios, 2);

        Order order2 = new Order(LocalDateTime.now().plusMinutes(20), LocalDateTime.now().plusHours(2));
        order2.addPizza(magherita, 1);
        order2.addPizza(bblCrust, 2);

        Order order3 = new Order(LocalDateTime.now().minusMinutes(11), LocalDateTime.now().plusHours(1));
        order3.addPizza(marios, 1);
        order3.addPizza(magherita, 5);

        Order order4 = new Order(LocalDateTime.now().plusMinutes(36), LocalDateTime.now().plusHours(1).plusMinutes(8));
        order4.addPizza(marios, 1);
        order4.addPizza(magherita, 2);

        Order order5 = new Order(LocalDateTime.now().plusMinutes(40), LocalDateTime.now().plusHours(3));
        order5.addPizza(marios, 2);
        order5.addPizza(bblCrust, 3);

        activeOrders.add(order1);
        activeOrders.add(order2);
        activeOrders.add(order3);
        activeOrders.add(order4);
        activeOrders.add(order5);

    }

    private Pizza getPizzaById(int id) {
        for (Pizza pizza : menu.getPizzaList()) {
            if (pizza.getPizzaID() == id) {
                return pizza;
            }
        }
        return null;
    }
}
