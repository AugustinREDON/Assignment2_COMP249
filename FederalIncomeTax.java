//--------------------------------------
// Assignment 2
// Written by: Augustin Redon 40240986 - Jacob Paterak 40268958
//--------------------------------------

// FederalIncomeTax class that extends the Deductions abstract class
public class FederalIncomeTax extends Deductions {
    double tax = 0;
    // Constructor to initialize the FederalIncomeTax object with an Employee
    public FederalIncomeTax(Employee employee) {
        super(employee); 
    }

    // Method to calculate the federal income tax
    @Override
    public double calculateTax() {

        double taxableIncome = employee.getGrossSalary();

        double income = employee.getGrossSalary();
        double tax = 0;
        // Apply the appropriate tax rate based on the taxable income brackets
        while (income != 0) {
            if (income > 253414) {
                tax += (income - 253415) * 0.33;
                income = 253414;
            }
            if (income > 177882 && income <= 253414) {
                tax += (income - 177883) * 0.29;
                income = 177882;
            }
            if (income > 114751 && income <= 177882) {
                tax += (income - 114750) * 0.26;
                income = 114750;
            }
            if (income > 57375 && income <= 114751) {
                tax += (income - 57375) * 0.205;
                income = 57375;
            }
            if (income > 16129 && income <= 57375) {
                tax += (income - 16129) * 0.15;
                income = 0;
            }
            // Income ≤ 16129 is not taxed

        }
        //return the amount of tax
    return tax;
    }

}
