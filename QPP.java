public class QPP extends Deductions{

    private static final double QPP_RATE = 0.108;
    private static final double MAX_QPP = 7700.40;

    public QPP(Employee employee){
        super(employee);
    }

    @Override
    public double calculateTax(){
        double QPP_Deduction = employee.getGrossSalary() * QPP_RATE;
        return Math.min(MAX_QPP, QPP_Deduction);
    }

}
