import javax.swing.*;              // For Swing dialogs and components
import java.awt.Dimension;         // For setting component dimensions
import java.io.PrintWriter;         // For writing to a file
import java.io.FileNotFoundException; // For handling file I/O errors

/* RestaurantOrderSystem is the main class for placing orders and setting appointments.*/
public class RestaurantOrderSystem {
    public static void main(String[] args) {
        // Array to store up to 10 orders.
        Order[] orders = new Order[10];
        int orderCount = 0; // keep track of the number of orders
        
        // Arrays for menu and their prices.
        String[] menuItems = {"Burger", "Lobster", "Ribs", "cereal"};
        double[] menuPrices = {5.99, 8.99, 4.99, 99.99};
        
        // Use a loop with Swing dialogs to allow the user to add multiple orders
        boolean ordering = true;
        while (ordering && orderCount < orders.length) {
            // menu string for display.
            String menu = "Menu Options:\n";
            for (int i = 0; i < menuItems.length; i++){
                menu += (i + 1) + ". " + menuItems[i] + " - $" + menuPrices[i] + "\n";
            }
            menu += "5. Finish Order and Set Appointment";
            
            // Show dialog for the menu selection.
            String inputChoice = JOptionPane.showInputDialog(null, menu, "Restaurant Order", JOptionPane.QUESTION_MESSAGE);
            if (inputChoice == null) { // User Canceled
                ordering = false;
                break;
            }
            
            int choice = 0;
            try {
                choice = Integer.parseInt(inputChoice);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number from 1 to 5.");
                continue;
            }
            
            // Process the user's menu choice.
            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                    int index = choice - 1; // Convert choice to array index.
                    String inputQuantity = JOptionPane.showInputDialog(null, "Enter quantity for " + menuItems[index] + ":", "Quantity", JOptionPane.QUESTION_MESSAGE);
                    if (inputQuantity == null) break; // Cancel current order.
                    
                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(inputQuantity);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid number.");
                        break;
                    }
                    
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a positive number.");
                        break;
                    }
                    
                    // Creates a new Order object and stores it in the orders array
                    orders[orderCount] = new Order(menuItems[index], menuPrices[index], quantity);
                    orderCount++; // Increase the order count.
                    JOptionPane.showMessageDialog(null, quantity + " " + menuItems[index] + "(s) added to your order.");
                    break;
                case 5:
                    ordering = false; // Exit the loop.
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid menu option.");
                    break;
            }
        }
        
        // If no orders were placed, exists the program
        if (orderCount == 0) {
            JOptionPane.showMessageDialog(null, "No orders placed. Exiting.");
            return;
        }
        
        // Collect appointment details using Swing
        String date = JOptionPane.showInputDialog(null, "Enter appointment date (dd/mm/yyyy):", "Appointment Date", JOptionPane.QUESTION_MESSAGE);
        if (date == null) date = "";
        String time = JOptionPane.showInputDialog(null, "Enter appointment time (HH:mm, 24-hour format):", "Appointment Time", JOptionPane.QUESTION_MESSAGE);
        if (time == null) time = "";
        
        // Create an Appointment object with the details
        Appointment appointment = new Appointment(date, time);
        
        // Builds a string that summarizes the orders and appointment
        String summary = "--- Order Summary ---\n";
        double grandTotal = 0.0;
        for (int i = 0; i < orderCount; i++) {
            summary += "Item: " + orders[i].getServiceName() +
                       ", Quantity: " + orders[i].getQuantity() +
                       ", Price per item: $" + orders[i].getPrice() +
                       ", Total: $" + orders[i].getTotalPrice() + "\n";
            grandTotal += orders[i].getTotalPrice();
        }
        summary += "Grand Total: $" + grandTotal + "\n\n";
        summary += "--- Appointment Details ---\n";
        summary += "Date: " + date + "\n";
        summary += "Time: " + time + "\n";
        
        // Display the summary in a JTextArea within a JScrollPane
        JTextArea textArea = new JTextArea(summary);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Order Summary", JOptionPane.INFORMATION_MESSAGE);
        
        // writes the order summary and appointment stuff into a text file
        try {
            PrintWriter writer = new PrintWriter("order_summary.txt");
            writer.println(summary);
            writer.close();
            JOptionPane.showMessageDialog(null, "Order summary and appointment details have been written to order_summary.txt");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error writing into file: " + e.getMessage());
        }
    }
}
