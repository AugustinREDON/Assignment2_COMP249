//--------------------------------------
// Assignment 2
// Written by: Augustin Redon 40240986 - Jacob Paterak 40268958
//--------------------------------------

// QPP class that extends the Deductions abstract class
public class QPP extends Deductions {

    private static final double QPP_RATE = 0.108;
    private static final double MAX_QPP = 7700.40;

    // Constructor to initialize the QPP object with an Employee
    public QPP(Employee employee) {
        super(employee); 
    }

    // Method to calculate the QPP deduction
    @Override
    public double calculateTax() {
        double QPP_Deduction = employee.getGrossSalary() * QPP_RATE;
        return Math.min(MAX_QPP, QPP_Deduction);
    }
}