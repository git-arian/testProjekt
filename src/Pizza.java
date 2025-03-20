public class Pizza {

    private int pizzaID;
    private String pizzaNavn;
    private double price;

    public Pizza (int id, String pizzaNavn, double price) {
        this.pizzaID = id;
        this.pizzaNavn = pizzaNavn;
        this.price = price;
    }

    public int getPizzaID() {
        return pizzaID;
    }

    public String getPizzaNavn() {
        return pizzaNavn;
    }

    public double getPrice() {
        return price;
    }

    public void setPizzaID(int pizzaID) {
        this.pizzaID = pizzaID;
    }

    public void setPizzaNavn(String pizzaNavn) {
        this.pizzaNavn = pizzaNavn;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "#" + pizzaID + ". " + pizzaNavn + " " + price + ",-";
    }
}
