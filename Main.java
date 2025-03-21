//
// Assignment 2
// Written by: Augustin Redon 40240986 - 
//

import java.util.Scanner;
import java.io.*;

class MinimumWageException extends Exception{
    public MinimumWageException(String m){
        super(m);
    }
}

public class Main {

    public static void main(String[] args)throws FileNotFoundException,IOException{

        BufferedReader br = new BufferedReader(new FileReader("payroll.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("payrollReport.txt"));



        Employee[] employees = new Employee[10];
        int count = 0;
        int totalLinesRead = -1;
        int errorLinesRead = -1;
        boolean caught = true;


        //File Paths
        String filePath = "payroll.txt";
        String errorFile = "payrollError.txt";


        try{
            
            Scanner sc = new Scanner(br);
            System.out.println("> Opening file payroll...");

            if(!sc.hasNext()){
                System.out.println("Error: Empty file!");
                sc.close();
                return;

            }
            BufferedReader br2 = new BufferedReader(new FileReader(errorFile));
            Scanner Error = new Scanner(br2);
            BufferedWriter bwError = new BufferedWriter(new FileWriter(errorFile));
            bwError.write("> Error lines found in payroll \n");
            System.out.println("> Reading file payroll...");

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                totalLinesRead++;
                try {
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter("\\s+");
                    Long EmployeeNumber = lineScanner.nextLong();
                    String EmployeeFirstName = lineScanner.next();
                    String EmployeeLastName = lineScanner.next();
                    double HoursWorked = lineScanner.nextDouble();
                    double HourlyWage = lineScanner.nextDouble();

                    if(HourlyWage  < 15.75){
                        lineScanner.close();
                        throw new MinimumWageException("Error: " + EmployeeFirstName + " " + EmployeeLastName + "is paid below minimum wage.");
                    }

                    employees[count++] = new Employee(EmployeeNumber, EmployeeFirstName, EmployeeLastName, HourlyWage, HoursWorked);
                    lineScanner.close();
                } catch (Exception e){
                    errorLinesRead++;
                   if(caught)
                       System.out.println("> Error lines found in file payroll");
                   caught = false;
                    try{
                        bwError.write(line.toUpperCase() + "\n");
                        System.out.println(line);
                    } catch (IOException ioException){
                        System.out.println("Failed to write to the error file: " + ioException.getMessage());
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
                    emp.getEmployeeId(), emp.getFirstName().toUpperCase(), emp.getLastName().toUpperCase(),
                    emp.getGrossSalary(), totalDeductions, netSalary));
          
            }
            System.out.println(totalLinesRead + " Lines read from file payroll");
            System.out.println(errorLinesRead + " Lines written to error file");
            System.out.println("Payroll processing completed. Check payrollReport.txt and payrollError.txt");
            
            Error.close();
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
