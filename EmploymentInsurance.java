public class EmploymentInsurance extends Deductions{

    public double calculateTax(Employee e) {

        if(e.getGrossSalary()<65700)
        {
            return e.getGrossSalary() * 0.0164;
        }
        else
        {
            return 1077.48;
        }
    }


}
