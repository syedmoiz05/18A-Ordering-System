/* Order class showing a customer's order
    it can print an order summary

   Constructor:
   - serviceName: the name of the ordered item.
   - price: the price for an item.
   - quantity: the number of items ordered.
*/
public class Order extends Service implements Printable {
    // Private field to store the quantity ordered.
    private int quantity;
    
    // Constructor to initialize the details
    public Order(String serviceName, double price, int quantity) {
        super(serviceName, price); // Call to the parent class (Service) constructor.
        this.quantity = quantity;
    }
    
    // Getter for quantity.
    public int getQuantity() {
        return quantity;
    }
    
    // a method to calculate the total price
    public double getTotalPrice() {
        return quantity * price;
    }
    
    // prints the order summary
    public void printSummary() {
        System.out.println("Order Summary:");
        System.out.println("Item: " + serviceName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price per item: $" + price);
        System.out.println("Total: $" + getTotalPrice());
    }
}
