//--------------------------------------
// Assignment 2
// Written by: Augustin Redon 40240986 - 
//--------------------------------------

// FederalIncomeTax class that extends the Deductions abstract class
public class FederalIncomeTax extends Deductions {

    // Constructor to initialize the FederalIncomeTax object with an Employee
    public FederalIncomeTax(Employee employee) {
        super(employee); 
    }

    // Method to calculate the federal income tax
    @Override
    public double calculateTax() {
        double taxableIncome = employee.getGrossSalary();

        // Apply the appropriate tax rate based on the taxable income brackets
        if (taxableIncome <= 16129) {
            return 0;
        } else if (taxableIncome > 16129 && taxableIncome <= 57375) {
            return taxableIncome * 0.15;
        } else if (taxableIncome > 57375 && taxableIncome <= 114750) {
            return taxableIncome * 0.205;
        } else if (taxableIncome > 114750 && taxableIncome <= 177882) {
            return taxableIncome * 0.26;
        } else if (taxableIncome > 177882 && taxableIncome <= 253414) {
            return taxableIncome * 0.29;
        } else {
            return taxableIncome * 0.33;
        }
    }
}
