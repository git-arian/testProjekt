import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Menu menu;
    private OrderList orderList;
    private OrderHistory orderHistory;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.menu = new Menu(scanner);
        this.orderHistory = new OrderHistory(scanner, menu);
        this.orderList = new OrderList(scanner, menu, orderHistory);
    }

    public void mainMenu() {

        while (true) {
            System.out.println("Mario's Admin Panel");
            System.out.println("1. Menu overview ");
            System.out.println("2. Manage orders");
            System.out.println("3. Exit");
            System.out.println("Choose (1-3)");
            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1 -> menuOverview();
                    case 2 -> manageOrders();
                    case 3 -> {
                        System.out.println("...exiting...");
                        return;
                    }
                    default -> System.out.println("Error: Only numbers (1-3) allowed");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Only numbers allowed!");
                scanner.nextLine();
            }
        }
    }

    private void menuOverview() {
        menu.menuOverview();
    }

    private void manageOrders() {
        orderList.manageOrders();
    }
}
