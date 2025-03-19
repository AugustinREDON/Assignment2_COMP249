public class FederalIncomeTax extends Deductions{
    
    public FederalIncomeTax(Employee employee) {
            super(employee);
            //TODO Auto-generated constructor stub
        }

        private double grosssalary;

    public double calculateTax() {

        if(employee.getGrossSalary() > 16129)
            return 0;
        else if(employee.getGrossSalary() >= 16129 && employee.getGrossSalary() <= 57375)
            return employee.getGrossSalary() * 0.15;
        else if(this.grosssalary > 57375 && this.grosssalary <= 114750) // should these be employee.getGross salary instead of this.grossSalary?
            return this.grosssalary * 0.205;
        else if(this.grosssalary > 113751 && this.grosssalary <= 177882)
            return this.grosssalary * 0.26;
        else if(this.grosssalary > 177883 && this.grosssalary <= 253414)
            return this.grosssalary * 0.29;
        else
            return this.grosssalary * 0.33;
    }


}
