import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private ArrayList<Pizza> pizzaList = new ArrayList<>();

    public Menu(Scanner scanner) {
        this.scanner = scanner;
        pizzaList.add(new Pizza(1, "At Mario's", 70));
        pizzaList.add(new Pizza(2, "Magherita", 49.95));
        pizzaList.add(new Pizza(3, "Napolitano", 75));
        pizzaList.add(new Pizza(4, "Salat Pizza", 75));
        pizzaList.add(new Pizza(5, "Pepperoni Pizza", 75));
        pizzaList.add(new Pizza(6, "Vegansk Pizza", 75));
        pizzaList.add(new Pizza(7, "BBL Crust Cheese", 149.95));
    }

    public void menuOverview() {
        boolean b = true;
        while (b) {
            System.out.println();
            System.out.println("1. Show menu card");
            System.out.println("2. Edit price of pizza");
            System.out.println("3. Return to main menu");
            System.out.println("Choose (1-3):");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1 -> showMenuCard();
                    case 2 -> editPizzaPrice();
                    case 3 -> b = false;
                    default -> System.out.println("Error: Only numbers (1-3) allowed.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Only numbers allowed!");
                scanner.nextLine();
            }
        }
    }

    public void showMenuCard() {
        System.out.println("===== MENU =====");
        for (Pizza pizza : pizzaList) {
            System.out.println(pizza);
        }
        System.out.println("================");
    }

    private void editPizzaPrice() {
        System.out.println("Enter pizza ID:");
        int pizzaIDToEdit = scanner.nextInt();

        boolean found = false;
        for (Pizza pizza : pizzaList) {
            if (pizza.getPizzaID() == pizzaIDToEdit) {
                System.out.println(pizza + " found!");
                System.out.println("What would you like to change the price to?");
                double newPrice = scanner.nextDouble();
                pizza.setPizzaPrice(newPrice);
                System.out.println("Price succesfully changed to " + pizza.getPizzaPrice());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Pizza with ID " + pizzaIDToEdit + " not found!");
        }
    }

    public ArrayList<Pizza> getPizzaList() {
        return pizzaList;
    }
}
