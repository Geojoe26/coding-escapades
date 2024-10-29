import java.util.*;
import java.util.stream.*;

public class App {

    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();

        // TODO
        // QnA

        //Q1
        List<Project> projNames = new ArrayList<>();

        List<List<Project>> proj = employeeList.stream()
                .map(Employee::getProjects)
                .toList();

        for(List<Project> l:proj){
            projNames.addAll(l);
        }

        System.out.println("List of Project Names in Non-Ascending Order:");
        projNames.stream()
                .map(Project::getName)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        //Q2
        System.out.println("Employee first names starting with A:");
        employeeList.stream()
                .map(x->x.getFirstName()+" "+x.getLastName())
                .filter(x->x.startsWith("A"))
                .forEach(System.out::println);

        //Q3
        System.out.println("List of Employees who joined in 2023:");
        employeeList.stream()
                .filter(x->x.getId().startsWith("2023"))
                .map(x->x.getFirstName()+" "+x.getLastName())
                .forEach(System.out::println);

        //Q4
        System.out.println("Sort by First Name and then by Salary");
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getFirstName)
                        .thenComparing(Employee::getSalary))
                .map(x->x.getFirstName()+" "+x.getSalary())
                .forEach(System.out::println);

        //Q5
        System.out.println("Names of employees having 3rd Highest Salary:");
        int n = 3;
        Optional<Integer> first = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getSalary)
                .distinct()
                .limit(n)
                .skip(n - 1)
                .findFirst();

        if(first.isPresent())
        {
            int salary = first.get();
            employeeList.stream()
                    .filter(x->x.getSalary() == salary)
                    .map(x->x.getFirstName()+" "+x.getLastName()+" "+x.getSalary())
                    .forEach(System.out::println);
        }
        else{
            System.out.println("Doesn't Exist");
        }

        //Q6
        Optional<Employee> min = employeeList.stream()
                .min(Comparator.comparing(Employee::getSalary));

        System.out.println(min.isPresent()?"Minimum Salary is as follows:"+min.get().getSalary():0.0);

        //Q7
        Optional<Integer> leastSal = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .distinct()
                .findFirst();

        System.out.println("Employees with least salary are as follows:");
        if(leastSal.isPresent())
        {
            employeeList.stream()
                    .filter(x->x.getSalary() == leastSal.get())
                    .map(x->x.getFirstName()+" "+x.getLastName()+" "+x.getSalary())
                    .forEach(System.out::println);
        }
        else{
            System.out.println("Doesn't Exist");
        }

        //Q8
        System.out.println("List of Employees working on more than 2 projects:");
        employeeList.stream()
                .filter(x->x.getProjects().size() > 2)
                .map(x->x.getFirstName()+" "+x.getLastName())
                .forEach(System.out::println);

        //Q9
        int sum = employeeList.stream()
                .mapToInt(Employee::getTotalLaptopsAssigned)
                .sum();

        System.out.println("Total Laptops assigned to employees:"+sum);

        //Q10
        String name = "Robert Downey Jr";
        System.out.println("Number of Projects with "+name+" as PM:"+
        projNames.stream()
                .filter(x->x.getProjectManager().equals(name))
                .map(x->x.getName()+" "+x.getProjectManager())
                .distinct()
                .count());

        //Q11
        System.out.println("List of Projects with "+name+" as PM:");
                projNames.stream()
                        .filter(x->x.getProjectManager().equals(name))
                        .map(Project::getName)
                        .distinct()
                        .forEach(System.out::println);

        //Q12
        List<String> quest12 = projNames.stream()
                .filter(x->x.getProjectManager().equals(name))
                .map(Project::getName)
                .distinct()
                .toList();

        System.out.println("List of employees working with "+name);
        for (Employee employee : employeeList) {
            for (Project p : employee.getProjects()) {
                if (quest12.contains(p.getName())) {
                    System.out.println(employee.getFirstName() + " " + employee.getLastName());
                }
            }
        }

        //Q13
        Map<String, List<Employee>> yearEmp = employeeList.stream()
                .collect(Collectors.groupingBy(x->x.getId().substring(0,4),
                        Collectors.mapping(employee -> employee, Collectors.toList())));

        for (Map.Entry<String, List<Employee>> entry : yearEmp.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(System.out::println);
        }

        //Q14
        Map<String, Long> yearCount = employeeList.stream()
                .collect(Collectors.groupingBy(x->x.getId().substring(0,4), Collectors.counting()));

        yearCount.entrySet().forEach(System.out::println);
    }
}
