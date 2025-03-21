public class Pizza {

    private int pizzaID;
    private String pizzaName;
    private double pizzaPrice;

    public Pizza (int id, String pizzaName, double pizzaPrice) {
        this.pizzaID = id;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
    }

    public int getPizzaID() {
        return pizzaID;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    @Override
    public String toString() {
        return "#" + pizzaID + ". " + pizzaName + " " + pizzaPrice + ",-";
    }
}
