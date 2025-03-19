public abstract class Deductions {
    
    protected Employee employee;

    //abstract class to be implemented by all children
    public abstract double calculateTax();

    //Constructor
    public Deductions(Employee employee){
        this.employee = employee;
    }




}
