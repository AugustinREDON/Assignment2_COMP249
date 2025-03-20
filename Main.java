import java.util.Scanner;
import java.io.*;

class MinimumWageException extends Exception{
    public MinimumWageException(String m){
        super(m);
    }
}

public class Main {

    public static void main(String[] args)throws FileNotFoundException,IOException{
        System.out.println("Test");


        BufferedReader br = new BufferedReader(new FileReader("payroll.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("payrollReport.txt"));



        Employee[] employees = new Employee[10];
        int count = 0;


        //File Paths
        String filePath = "payroll.txt";
        String reportPath = "payrollReport.txt";
        String errorFIle = "payrollError.txt";


        try{

            Scanner sc = new Scanner(br);
            System.out.println("> Opening file payroll...");

            if(!sc.hasNext()){
                System.out.println("Error: Empty file!");
                sc.close();
                return;

            }

            BufferedWriter bwError = new BufferedWriter(new FileWriter(errorFIle));
            System.out.println("> Reading file payroll...");

            while(sc.hasNextLine()) {
                try {
                    Long EmployeeNumber = sc.nextLong();
                    String EmployeeFirstName = sc.next();
                    String EmployeeLastName = sc.next();
                    double HoursWorked = sc.nextDouble();
                    double HourlyWage = sc.nextDouble();

                    if(HourlyWage  < 15.75){
                        throw new MinimumWageException("Error: " + EmployeeFirstName + " " + EmployeeLastName + "is paid below minimum wage.");
                    }

                    employees[count++] = new Employee(EmployeeNumber, EmployeeFirstName, EmployeeLastName, HourlyWage, HoursWorked);
                    //employees[count++] = emp;
                } catch (Exception e){

                    bwError.write(e.getMessage() + "\n");
                    System.out.println(e.getMessage() + "while loop error");

                    if(sc.hasNextLine()){
                        sc.nextLine();
                    }
  
                }

            }

            bw.write(String.format("%50s\n", "iDroid Solutions"));
            bw.write(String.format("%55s\n", "-----------------------"));
            bw.write(String.format("%-20s %-15s %-15s %-15s %-15s %-15s\n", 
                "Employee Number", "First name", "Last Name", "Gross salary", "Deductions", "Net salary"));
            bw.write("-------------------------------------------------------------------------------------------------------\n");
 
            for(int i = 0; i < count; i++){

                Employee emp = employees[i];

                // Create deduction objects for this employee
                EI ei = new EI(emp);
                QPP qpp = new QPP(emp);
                QPIP qpip = new QPIP(emp);
                ProvincialIncomeTax provTax = new ProvincialIncomeTax(emp);
                FederalIncomeTax fedTax = new FederalIncomeTax(emp);

                // Calculate total deductions
                double totalDeductions = ei.calculateTax() + qpp.calculateTax() +
                      qpip.calculateTax() + provTax.calculateTax() + fedTax.calculateTax();

                // Calculate net salary
                double netSalary = emp.getGrossSalary() - totalDeductions;

                // Write to report file
                bw.write(String.format("%-20s %-15s %-15s $%-14.2f $%-14.2f $%-14.2f\n",
                    emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(),
                    emp.getGrossSalary(), totalDeductions, netSalary));
          
            }

            System.out.println("Payroll processing completed. Check payrollReport.txt and payrollError.txt");
            
            bwError.close();
            sc.close();
        } catch(FileNotFoundException e){
            System.out.println("Error: file " + filePath + "not found");
        } catch (IOException e){
            System.out.println("Error writing to files");
        } 


        bw.close();
    }
    
}
