import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderList {
    private ArrayList<Order> activeOrders;
    private ArrayList<Order> completedOrders;
    private Scanner scanner;
    private Menu menu;

    public OrderList(Scanner scanner, Menu menu) {
        this.scanner = scanner;
        this.menu = menu;
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
            System.out.println("3. View completed orders");
            System.out.println("4. Return to main menu");
            System.out.println("Choose (1-4):");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1 -> showActiveOrders(activeOrders);
                    case 2 -> completeOrder();
                    case 3 -> showCompleteOrders();
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

            boolean found = false;
            for (int i = 0; i < activeOrders.size(); i++) {
                if (activeOrders.get(i).getOrderID() == orderID) {
                    Order orderToMove = activeOrders.get(i);
                    activeOrders.remove(i);
                    completedOrders.add(orderToMove);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Order with " + orderID + " not found!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.nextLine();
        }
    }


    private void showCompleteOrders() {

        if (completedOrders.isEmpty()) {
            System.out.println("No active orders!");
            return;
        }

        System.out.println("==== COMPLETE ORDERS ===");
        for (Order order : completedOrders) {
            System.out.println(order);
            System.out.println("====================");
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

        Order order3 = new Order(LocalDateTime.now().plusMinutes(22), LocalDateTime.now().plusMinutes(40));
        order3.addPizza(marios, 1);

        activeOrders.add(order1);
        activeOrders.add(order2);
        activeOrders.add(order3);
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
