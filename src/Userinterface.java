import java.util.InputMismatchException;
import java.util.Scanner;

public class Userinterface {
    private Scanner scanner;
    private Menu menu;

    public Userinterface() {
        this.scanner = new Scanner(System.in);
        this.menu = new Menu(scanner);
    }

    public void mainMenu() {

        while (true) {
            System.out.println("1. Menu overview ");
            System.out.println("2. Manage orders");
            System.out.println("Enter numbers from 1 to 2");
            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1 -> goToMenuOverview();
                    case 2 -> manageOrders();
                    case 3 -> {
                        return;
                    }
                    default -> System.out.println("Error(*main menu*): Only numbers are from 1 to 2 allowed");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error(*main menu*): Only numbers allowed!");
                scanner.nextLine();
            }
        }
    }

    private void goToMenuOverview() {
        menu.menuOverview();
    }

    private void manageOrders() {

    }
}
