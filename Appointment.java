/* Appointment class meant for the delivery or to service appointment
   It implements both Schedulable and Printable interfaces.
   
   Constructor:
   - appointmentDate: the date of the appointment.
   - appointmentTime: the time of the appointment.
*/
public class Appointment implements Schedulable, Printable {
    // Private fields to store appointment details
    private String appointmentDate;
    private String appointmentTime;
    
    // Constructor to initialize the appointment
    public Appointment(String appointmentDate, String appointmentTime) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }
    
    // Method to schedule the appointment
    public void schedule() {
        System.out.println("Appointment scheduled for " + appointmentDate + " at " + appointmentTime);
    }
    
    // Method to print the appointment details
    public void printSummary() {
        System.out.println("Appointment Details:");
        System.out.println("Date: " + appointmentDate);
        System.out.println("Time: " + appointmentTime);
    }
}
