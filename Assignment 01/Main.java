import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // create an input selection and store it in var Selection
        Scanner input = new Scanner(System.in);

        //create dummy users and add to list
        List<Employee> emplist = new ArrayList<>();

        Employee Employee = new Employee("Mason Bridwell", "Janitor", "123-43-5346");
        Employee SalariedFull = new SalariedFull("D'vin Johnson", "Developer", "987-65-4321", 40, 100000);
        Employee SalariedPart = new SalariedPart("Phil Grim", "Professor", "432-75-7644", 20, 50000);
        Employee Contractor = new Contractor("Joe Schmoe", "Full-Stack Dev", "654-34-2349", 10);

        emplist.add(Employee);
        emplist.add(SalariedFull);
        emplist.add(SalariedPart);
        emplist.add(Contractor);

        while (true) {
            System.out.println("Select An Option: 1|View Users  2|Add User  3|Delete User");
            String selection = input.nextLine();

            //view users and their types
            if (selection.equals("1")) {
                for (Employee emp : emplist) {
                    System.out.println("Name: " + emp.getName());
                    System.out.println("Job Position: " + emp.getjob_title());
                    System.out.println("SSN: " + emp.getSsn());

                    if (emp instanceof SalariedFull) {
                        System.out.println("Hours worked per week: " + ((SalariedFull) emp).getWeekTimeWorked());
                        System.out.println("Salary: " + ((SalariedFull) emp).getsalary());
                    } else if (emp instanceof SalariedPart) {
                        System.out.println("Hours worked per week: " + ((SalariedPart) emp).getWeekTimeWorked());
                        System.out.println("Salary: " + ((SalariedPart) emp).getsalary());
                    } else if (emp instanceof Contractor) {   
                        System.out.println("Hourly Rate: " + ((Contractor) emp).getHourly());
                    }

                    System.out.println(); 
                }
            }

            else if (selection.equals("2")) {
                Scanner Option = new Scanner(System.in);
                System.out.println("Select a type: 1|Standard Employee  2|SalariedFull 3|SalariedPart 4|Contractor");

                String accountSelection = Option.nextLine();

                if (accountSelection.equals("1")) {
                    // add standard e
                    System.out.println("Please type name: ");
                    String name = input.nextLine();


                    System.out.println("Please type Job Position: ");
                    String JobPosition = input.nextLine();

                    
                    System.out.println("Please type SSN: ");
                    String SSN = input.nextLine();


                    emplist.add(new Employee(name, JobPosition, SSN));



                } else if (accountSelection.equals("2")) {
                    // SalariedFull
                    System.out.println("Please type name: ");
                    String name = input.nextLine();


                    System.out.println("Please type Job Position: ");
                    String JobPosition = input.nextLine();


                    System.out.println("Please type SSN: ");
                    String SSN = input.nextLine();


                    System.out.println("Please type hours worked per week: ");
                    int HoursWorked = input.nextInt();


                    System.out.println("Please type salary: ");
                    int Salary = input.nextInt();


                    emplist.add(new SalariedFull(name, JobPosition, SSN, HoursWorked, Salary));
                    input.nextLine(); 




                } else if (accountSelection.equals("3")) {
                    // Add SalariedPart
                    System.out.println("Please type name: ");
                    String name = input.nextLine();


                    System.out.println("Please type Job Position: ");
                    String JobPosition = input.nextLine();


                    System.out.println("Please type SSN: ");
                    String SSN = input.nextLine();


                    System.out.println("Please type hours worked per week: ");
                    int HoursWorked = input.nextInt();


                    System.out.println("Please type salary: ");
                    int Salary = input.nextInt();

                    
                    emplist.add(new SalariedPart(name, JobPosition, SSN, HoursWorked, Salary));
                    input.nextLine(); 






                } else if (accountSelection.equals("4")) {
                    // Add contractor 
                    System.out.println("Please type name: ");
                    String name = input.nextLine();


                    System.out.println("Please type Job Position: ");
                    String JobPosition = input.nextLine();


                    System.out.println("Please type SSN: ");
                    String SSN = input.nextLine();


                    System.out.println("Please type Hourly Rate: ");
                    int Hourly = input.nextInt();


                    emplist.add(new Contractor(name, JobPosition, SSN, Hourly));
                    input.nextLine(); 
                }
            }

            else if (selection.equals("3")) {
                //delete user 
                System.out.println("Type a name of the user you want to delete: ");
                String nameSelection = input.nextLine();
                emplist.removeIf(employee -> employee.getName().equals(nameSelection));
            }

        }
    }
}