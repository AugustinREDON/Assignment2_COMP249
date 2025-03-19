import java.util.Scanner;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;

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
        Employee e1;

        //File Paths
        String filePath = "payroll.txt";
        String reportPath = "payrollReport.txt";
        String errorFIle = "payrollError.txt";


        try{

            Scanner sc = new Scanner(br);

            if(!sc.hasNext()){
                System.out.println("Error: Empty file!");
                return;
            }

            while(sc.hasNextLine()) {
                try {
                    Long EmployeeNumber = sc.nextLong();
                    String EmployeeFirstName = sc.next();
                    String EmployeeLastName = sc.next();
                    double HoursWorked = sc.nextDouble();
                    double HourlyWage = sc.nextDouble();

                    if(HourlyWage  < 15.75){
                        throw new MinimumWageException("You are paid below minimum wage!");
                    }

                    Employee emp = new Employee(EmployeeNumber, EmployeeFirstName, EmployeeLastName, HourlyWage, HoursWorked);
                    employees[count++] = emp;
                } catch (Exception e){
                    e.getMessage();
                }

            } 
 
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
                bw.write(String.format("%-10d %-10s %-10s $%-11.2f $%-11.2f $%-11.2f\n",
                      emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(),
                      emp.getGrossSalary(), totalDeductions, netSalary));
            }

            System.out.println("Payroll processing completed. Check payrollReport.txt and payrollError.txt");

        } catch(FileNotFoundException e){
            System.out.println("Error: file " + filePath + "not found");
        } catch (IOException e){
            System.out.println("Error writing to files");
        }


    }
    
}
