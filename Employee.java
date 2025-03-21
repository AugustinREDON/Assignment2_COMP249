//--------------------------------------
// Assignment 2
// Written by: Augustin Redon 40240986 - Jacob Paterak 40268958
//--------------------------------------

// Employee class to represent an employee's details and calculate their gross salary
public class Employee {

    // Fields to store employee details
    private long EmployeeId;       
    private String FirstName;      
    private String LastName;       
    private double HourlyWage;     
    private double HoursWorked;    
    private double GrossSalary;    
    final int week = 52;           

    // Constructor to initialize an Employee object
    public Employee(Long id, String firstName, String lastName, double HourlyWage, double HoursWorked) {
        this.EmployeeId = id;                      
        this.FirstName = firstName;                
        this.LastName = lastName;                  
        this.HourlyWage = HourlyWage;              
        this.HoursWorked = HoursWorked;            
        this.GrossSalary = HourlyWage * HoursWorked * week;  
    }

    // Getter methods to retrieve employee information
    public double getGrossSalary() {
        return this.GrossSalary; 
    }

    public double getEmployeeId() {
        return EmployeeId; 
    }

    public String getFirstName() {
        return FirstName; 
    }

    public String getLastName() {
        return LastName; 
    }

    // Override the toString() method to provide a string representation of the employee
    @Override
    public String toString() {
        return EmployeeId + "\t" + FirstName + "\t" + LastName + "\t" + GrossSalary;
    }
}
