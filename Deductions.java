//
// Assignment 2
// Written by: Augustin Redon 40240986 - Jacob Paterak 40268958
//

// Abstract class 
public abstract class Deductions {
    
    // Protected field to store the employee 
    protected Employee employee;

    // Abstract method to calculate tax
    // This method must be implemented by all subclasses of Deductions
    public abstract double calculateTax();

    // Constructor to initialize the employee field
    public Deductions(Employee employee){
        this.employee = employee; 
    }

}
