import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderHistory {
    public ArrayList<Order> completedOrders;

public OrderHistory() {
    this.completedOrders = new ArrayList<>();
}

    public void showCompleteOrders() {

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
        System.out.println("Total revenue thus far: " + totalRevenue);
    }

    public void saveCompletedOrders() {
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

    public void loadCompletedOrders() {
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
}
