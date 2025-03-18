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

        BufferedReader br = new BufferedReader(new FileReader("payroll.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("payrollReport.txt"));

        Scanner sc = new Scanner(br);
        Employee[] Employees = new Employee[10];
        int count = 0;
        Employee e1;
        String filePath = "payroll.txt";


        try{

            if(sc.hasNextLine()) {
                long numLines = Files.lines(Paths.get(filePath)).count();
                for (int i = 0; i < numLines; i++) {
                    Long EmployeeNumber = sc.nextLong();
                    String EmployeeFirstName = sc.next();
                    String EmployeeLastName = sc.next();
                    double HoursWorked = sc.nextDouble();
                    double HourlyWage = sc.nextDouble();
                    if(HourlyWage>15.75) {

                        Employees[count] = new Employee(EmployeeNumber, EmployeeFirstName, EmployeeLastName, HoursWorked , HourlyWage);
                        count++;
                        }
                    else
                        throw new MinimumWageException("You are paid below minimum wage");


                }

            }
            else
                System.out.println("Empty file");
        }
       catch(Exception e)
        {

        }
        try
        {
            bw.write(" iDroid Solutions\n");
            bw.write("Employee Number\t First Name\t Last Name\t Gross salary\t Deductions\t Net salary\n");
            bw.write("--------------------------------------------------------------------------------------------------------------------\n");
            for(int i = 0; i<Employees.length; i++) {

                if(Employees[i]!=null) {
                    System.out.println(i);
                    bw.write(Employees[i].toString());
                }
            }

            bw.close();
        }
        catch(Exception e)
        {

        }


    }
    
}
