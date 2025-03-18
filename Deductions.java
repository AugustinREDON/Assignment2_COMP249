public abstract class Deductions {
    protected double grossSalary;

    //abstract class to be implemented by all children
    public abstract double calculateTax();

    //Constructor
    public Deductions(double grossSalary){
        this.grossSalary = grossSalary;
    }

    //Getter
    public double getGrossSalary(){
        return grossSalary;
    }

    public abstract double calculateTax(Employee employee);

}
