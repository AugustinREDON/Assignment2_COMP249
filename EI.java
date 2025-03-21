//--------------------------------------
// Assignment 2
// Written by: Augustin Redon 40240986 - 
//--------------------------------------

// EI (Employment Insurance) class that extends the Deductions abstract class
public class EI extends Deductions {

    // Constructor to initialize the EI o
    public EI(Employee employee) {
        super(employee);
    }

    // Method to calculate the EI deduction
    @Override
    public double calculateTax() {
        // If the employee's gross salary is less than $65,700
        if (employee.getGrossSalary() < 65700) {
            // Calculate EI as 1.64% of the gross salary
            return employee.getGrossSalary() * 0.0164;
        } else {
            // If the gross salary exceeds $65,700, return the maximum EI contribution of $1,077.48
            return 1077.48;
        }
    }
}
