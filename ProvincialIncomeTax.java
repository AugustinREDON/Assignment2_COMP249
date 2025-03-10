public class ProvincialIncomeTax extends Deductions{

    public ProvincialIncomeTax(double grossSalary){
        super(grossSalary);
    }

    @Override
    public double calculateTax(){
        double taxableIncome = grossSalary;
        double tax = 0.0;
        
        if(taxableIncome > 129590){
            tax += (taxableIncome - 129590) * 0.2575;
            taxableIncome = 129590;
        }
        
        return tax;
    }

}
