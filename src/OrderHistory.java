import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderHistory {
    public ArrayList<Order> completedOrders;
    Scanner scanner;
    Menu menu;

    public OrderHistory(Scanner scanner, Menu menu) {
        this.completedOrders = new ArrayList<>();
        this.scanner = scanner;
        this.menu = menu;
    }

    public void orderHistoryMenu() {
        boolean b = true;
        while (b) {
            System.out.println("1. View completed orders");
            System.out.println("2. Save current completed orders to file");
            System.out.println("3. Load a completed orders file ");
            System.out.println("4. Return to previous menu");
            System.out.println("Choose (1-4):");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1 -> showCompleteOrders();
                    case 2 -> saveCompletedOrders();
                    case 3 -> loadCompletedOrders();
                    case 4 -> b = false;
                    default -> System.out.println("Error: Only numbers (1-4) allowed.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Only numbers allowed!");
                scanner.nextLine();
            }
        }
    }

    private void showCompleteOrders() {
        if (completedOrders.isEmpty()) {
            System.out.println("No active orders!");
            return;
        }
        double totalRevenue = 0;
        System.out.println("==== COMPLETED ORDERS ===");
        for (Order order : completedOrders) {
            System.out.println(order);
            System.out.println("====================");
            totalRevenue += order.totalPrice();
        }
        System.out.println("========================");
        System.out.println("Total revenue thus far: " + totalRevenue);
        System.out.println("========================");
        showPopularPizzas();
    }

    private void saveCompletedOrders() {
        try {
            String filename = "completed_orders.txt";
            FileWriter writer = new FileWriter(filename);
            for (Order order : completedOrders) {
                writer.write(order.toString() + "\n");
                writer.write("====================\n");
            }
            writer.close();
            System.out.println("Completed orders saved to file " + filename);
        } catch (IOException e) {
            System.out.println("Error! Try again.");
        }
    }

    private void loadCompletedOrders() { // Mangler fuld implementering af parsing
        String filename = "completed_orders.txt";

        try (Scanner fileScanner = new Scanner(new File(filename))) {
            completedOrders.clear();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                if (line.startsWith("ORDER ID:")) {
                    completedOrders.add(new Order(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
                }
            }

            System.out.println("Loaded " + completedOrders.size() + " completed orders!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        }
    }

    public void showPopularPizzas() {
        int[] pizzaCounts = new int[menu.getPizzaList().size()];

        for (Order order : completedOrders) {
            for (int i = 0; i < order.getPizzas().size(); i++) {
                Pizza pizza = order.getPizzas().get(i);
                int quantity = order.getQuantities().get(i);

                pizzaCounts[pizza.getPizzaID() - 1] += quantity;
            }
        }

        for (int i = 0; i < pizzaCounts.length; i++) {
            Pizza pizza = menu.getPizzaList().get(i);
            System.out.println(pizza.getPizzaName() + ": " + pizzaCounts[i] + " sold");
        }
        System.out.println("========================");
    }
}
