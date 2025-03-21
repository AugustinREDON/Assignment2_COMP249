//--------------------------------------
// Assignment 2
// Written by: Augustin Redon 40240986 - 
//--------------------------------------

// ProvincialIncomeTax class that extends the Deductions abstract class
public class ProvincialIncomeTax extends Deductions {

    // Constructor to initialize the ProvincialIncomeTax object with an Employee
    public ProvincialIncomeTax(Employee employee) {
        super(employee); 
    }

    // Method to calculate the provincial income tax
    @Override
    public double calculateTax() {
        double taxableIncome = employee.getGrossSalary();
        
        if (taxableIncome > 18571 && taxableIncome <= 53255) {
            return taxableIncome * 0.14;
        } else if (taxableIncome > 53255 && taxableIncome <= 106495) {
            return taxableIncome * 0.19;
        } else if (taxableIncome > 106495 && taxableIncome <= 129590) {
            return taxableIncome * 0.24;
        } else if (taxableIncome > 129590) {
            return taxableIncome * 0.2575;
        } else {
            return 0.0;
        }
    }
}
