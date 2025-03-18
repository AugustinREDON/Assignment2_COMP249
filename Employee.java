public class Employee
{
    private long EmployeeId;
    private String FirstName;
    private String LastName;
    private double HourlyWage;
    private double HoursWorked;
    private double GrossSalary;
    final int week = 52;
    public Employee(Long id, String firstName, String lastName,double HourlyWage, double HoursWorked) {
        this.EmployeeId = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.HourlyWage = HourlyWage;
        this.HoursWorked = HoursWorked;
        this.GrossSalary = HourlyWage * HoursWorked *week;

    }
    public double getGrossSalary()
    {
        return this.GrossSalary;
    }
    public String toString()
    {
        return EmployeeId + "\t" + FirstName + "\t" + LastName + "\t" + GrossSalary;
    }
}
