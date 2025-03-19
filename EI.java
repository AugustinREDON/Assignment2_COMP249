public class EI extends Deductions{

    public EI(Employee employee) {
            super(employee);
            //TODO Auto-generated constructor stub
        }
    
        public double calculateTax() {

        if(employee.getGrossSalary()<65700){
            return employee.getGrossSalary() * 0.0164;
        } else {
            return 1077.48;
        }
    }

}
