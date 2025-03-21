//--------------------------------------
// Assignment 2
// Written by: Augustin Redon 40240986 - Jacob Paterak 40268958
//--------------------------------------

import java.util.Scanner;
import java.io.*;

// Custom exception to handle cases where an employee's hourly wage is below the minimum wage
class MinimumWageException extends Exception {
    public MinimumWageException(String m) {
        super(m);
    }
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // BufferedReader to read the input file (payroll.txt)
        BufferedReader br = new BufferedReader(new FileReader("payroll.txt"));
        // BufferedWriter to write the payroll report to an output file (payrollReport.txt)
        BufferedWriter bw = new BufferedWriter(new FileWriter("payrollReport.txt"));

        // Array to store Employee objects 
        Employee[] employees = new Employee[10];
        int count = 0; 
        int totalLinesRead = -1; 
        int errorLinesRead = -1; 
        boolean caught = true; 

        // File paths for input and error files
        String filePath = "payroll.txt";
        String errorFile = "payrollError.txt";

        try {
            // Scanner to read the input file line by line
            Scanner sc = new Scanner(br);
            System.out.println("> Opening file payroll...");

            // Check if the file is empty
            if (!sc.hasNext()) {
                System.out.println("Error: Empty file!");
                sc.close();
                return;
            }

            // BufferedReader and BufferedWriter for the error file
            BufferedReader br2 = new BufferedReader(new FileReader(errorFile));
            Scanner Error = new Scanner(br2);
            BufferedWriter bwError = new BufferedWriter(new FileWriter(errorFile));
            bwError.write("> Error lines found in payroll \n");
            System.out.println("> Reading file payroll...");

            // Process each line in the input file
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                totalLinesRead++;
                try {
                    // Scanner to parse individual fields from the line
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter("\\s+"); 

                    // Read employee details from the line
                    Long EmployeeNumber = lineScanner.nextLong();
                    String EmployeeFirstName = lineScanner.next();
                    String EmployeeLastName = lineScanner.next();
                    double HoursWorked = lineScanner.nextDouble();
                    double HourlyWage = lineScanner.nextDouble();

                    // Check if the hourly wage is below the minimum wage
                    if (HourlyWage < 15.75) {
                        lineScanner.close();
                        throw new MinimumWageException("Error: " + EmployeeFirstName + " " + EmployeeLastName + " is paid below minimum wage.");
                    }

                    // Create a new Employee object and add it to the array
                    employees[count++] = new Employee(EmployeeNumber, EmployeeFirstName, EmployeeLastName, HourlyWage, HoursWorked);
                    lineScanner.close();
                } catch (Exception e) {
                    // Handle errors 
                    errorLinesRead++;
                    if (caught) {
                        System.out.println("> Error lines found in file payroll");
                        caught = false;
                    }
                    try {
                        // Write the invalid line to the error file
                        bwError.write(line.toUpperCase() + "\n");
                        System.out.println(line);
                    } catch (IOException ioException) {
                        System.out.println("Failed to write to the error file: " + ioException.getMessage());
                    }
                }
            }

            // Write the header for the payroll report
            bw.write(String.format("%50s\n", "iDroid Solutions"));
            bw.write(String.format("%55s\n", "-----------------------"));
            bw.write(String.format("%-20s %-15s %-15s %-15s %-15s %-15s\n",
                    "Employee Number", "First name", "Last Name", "Gross salary", "Deductions", "Net salary"));
            bw.write("-------------------------------------------------------------------------------------------------------\n");

            // Process each valid employee and calculate deductions
            for (int i = 0; i < count; i++) {
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

                // Write the employee's details to the payroll report
                bw.write(String.format("%-20s %-15s %-15s $%-14.2f $%-14.2f $%-14.2f\n",
                        emp.getEmployeeId(), emp.getFirstName().toUpperCase(), emp.getLastName().toUpperCase(),
                        emp.getGrossSalary(), totalDeductions, netSalary));
            }

            // Print summary of the processing
            System.out.println(totalLinesRead + " Lines read from file payroll");
            System.out.println(errorLinesRead + " Lines written to error file");
            System.out.println("Payroll processing completed. Check payrollReport.txt and payrollError.txt");

            // Close resources
            Error.close();
            bwError.close();
            sc.close();
        } catch (FileNotFoundException e) {
            // Handle case where the input file is not found
            System.out.println("Error: file " + filePath + " not found");
        } catch (IOException e) {
            // Handle errors during file writing
            System.out.println("Error writing to files");
        }

        // Close the payroll report writer
        bw.close();
    }
}
