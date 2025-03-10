public class QPIP extends Deductions{

    private static final double QPIP_RATE = 0.00494;
    private static final double  MAX_QPIP = 494.12;

    public QPIP(double grossSalary){
        super(grossSalary);
    }

    @Override
    public double calculateTax(){
        double QPIP_Deduction = grossSalary * QPIP_RATE;
        return Math.min(MAX_QPIP, QPIP_Deduction);
    }


}
