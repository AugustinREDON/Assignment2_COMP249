public class FederalIncomeTax extends Deductions{
    private double grosssalary;
    public double calculateTax(Employee e) {

        if(e.getGrossSalary()>16129)
            return 0;
        else if(e.getGrossSalary()>=16129 && e.getGrossSalary()<=57375)
            return e.getGrossSalary()*0.15;
        else if(this.grosssalary>57375 && this.grosssalary<=114750)
            return this.grosssalary*0.205;
        else if(this.grosssalary>113751 && this.grosssalary<=177882)
            return this.grosssalary*0.26;
        else if(this.grosssalary>177883 && this.grosssalary<=253414)
            return this.grosssalary*0.29;
        else
            return this.grosssalary * 0.33;
    }


}
