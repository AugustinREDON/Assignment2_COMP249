public class FederalIncomeTax extends Deductions{
    
    public FederalIncomeTax(Employee employee) {
            super(employee);
            //TODO Auto-generated constructor stub
        }

    public double calculateTax() {

        double taxableIncome = employee.getGrossSalary();

        if(taxableIncome > 16129)
            return 0;
        else if(taxableIncome >= 16129 && taxableIncome <= 57375)
            return taxableIncome * 0.15;
        else if(taxableIncome > 57375 && taxableIncome <= 114750) // should these be employee.getGross salary instead of taxableIncome?
            return taxableIncome * 0.205;
        else if(taxableIncome > 113751 && taxableIncome <= 177882)
            return taxableIncome * 0.26;
        else if(taxableIncome > 177883 && taxableIncome <= 253414)
            return taxableIncome * 0.29;
        else
            return taxableIncome * 0.33;
    }


}
