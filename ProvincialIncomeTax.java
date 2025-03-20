public class ProvincialIncomeTax extends Deductions{

    public ProvincialIncomeTax(Employee employee){
        super(employee);
    }

    @Override
    public double calculateTax(){
        double taxableIncome = employee.getGrossSalary();
        
        if(taxableIncome > 18571 && taxableIncome <= 53255){
            return taxableIncome * 0.14;
        } else if (taxableIncome > 53255 && taxableIncome <= 106495){
            return taxableIncome * 0.19;
        } else if (taxableIncome > 106495 && taxableIncome <= 129590){
            return taxableIncome * 0.24;
        } else if (taxableIncome > 129590 ){
            return taxableIncome * 0.2575;
        } else {
            return 0.0;
        }
        
    }

}
