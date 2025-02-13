/* Service class for business service
   can be used by subclasses

   Constructor:
   - serviceName: the name of the service.
   - price: the price of the service or item.
*/
public class Service {
    // Protected fields so that the subclasses can access them
    protected String serviceName;
    protected double price;
    
    // Constructor to initialize the service
    public Service(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }
    
    // Getter method for the service name
    public String getServiceName() {
        return serviceName;
    }
    
    // Getter method for the price
    public double getPrice() {
        return price;
    }
}
