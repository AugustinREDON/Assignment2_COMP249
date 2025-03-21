//--------------------------------------
// Assignment 2
// Written by: Augustin Redon 40240986 - 
//--------------------------------------

// QPIP class that extends the Deductions abstract class
public class QPIP extends Deductions {

    private static final double QPIP_RATE = 0.00494;
    private static final double MAX_QPIP = 494.12;

    // Constructor to initialize the QPIP object with an Employee
    public QPIP(Employee employee) {
        super(employee); 
    }

    // Method to calculate the QPIP deduction
    @Override
    public double calculateTax() {
        double QPIP_Deduction = employee.getGrossSalary() * QPIP_RATE;
        return Math.min(MAX_QPIP, QPIP_Deduction);
    }
}
