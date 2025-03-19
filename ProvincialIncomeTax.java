public class ProvincialIncomeTax extends Deductions{

    public ProvincialIncomeTax(Employee employee){
        super(employee);
    }

    @Override
    public double calculateTax(){
        double taxableIncome = employee.getGrossSalary();
        double tax = 0.0;
        
        if(taxableIncome > 129590){
            tax += (taxableIncome - 129590) * 0.2575;
            taxableIncome = 129590;
        }
        
        return tax;
    }

}
