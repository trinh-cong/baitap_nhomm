package baimoi1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem {

    private ArrayList<Employee> employees = new ArrayList<>();
    private static final String FILE_PATH = "employees.txt";

    private static class Employee {
        String name;
        int id;
        String contact;
        String email;
        int salary;

        public Employee(String name, int id, String contact, String email, int salary) {
            this.name = name;
            this.id = id;
            this.contact = contact;
            this.email = email;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", contact='" + contact + '\'' +
                    ", email='" + email + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    private void readDataFromFile() {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arr = line.split(",");
                String name = arr[0];
                int id = Integer.parseInt(arr[1]);
                String contact = arr[2];
                String email = arr[3];
                int salary = Integer.parseInt(arr[4]);
                Employee emp = new Employee(name, id, contact, email, salary);
                employees.add(emp);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file " + FILE_PATH + " does not exist yet.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid data in file " + FILE_PATH + ": " + e.getMessage());
        }
    }

    private void saveDataToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Employee emp : employees) {
                writer.write(emp.name + "," + emp.id + "," + emp.contact + "," + emp.email + "," + emp.salary + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to save data to file " + FILE_PATH + ": " + e.getMessage());
        }
    }

    private void addEmployee(Scanner scanner) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter employee contact number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter employee email ID: ");
        String email = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        int salary = scanner.nextInt();
        scanner.nextLine();
        Employee emp = new Employee(name, id, contact, email, salary);
        employees.add(emp);
        System.out.println("Employee details added successfully");
    }

    private void searchEmployee(Scanner scanner) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter employee ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Employee emp = employees.stream().filter(e -> e.id == id).findFirst().orElse(null);
        if (emp != null) {
            System.out.println("Employee details for ID " + emp.id + ":");
            System.out.println(emp);
        } else {
            System.out.println("Employee with ID " + id + " not found");
        }
    }

    private void editEmployee(Scanner scanner) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter employee ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Employee emp = employees.stream().filter(e -> e.id == id).findFirst().orElse(null);
        if (emp != null) {
            System.out.print("Enter new salary: ");
            int salary = scanner.nextInt();
            scanner.nextLine();
            emp.salary = salary;
            System.out.println("Employee details for ID " + emp.id + " edited successfully");
        } else {
            System.out.println("Employee with ID " + id + " not found");
        }
    }

    private void deleteEmployee(Scanner scanner) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean removed = employees.removeIf(e -> e.id == id);
        if (removed) {
            System.out.println("Employee with ID " + id + " deleted successfully");
        } else {
            System.out.println("Employee with ID " + id + " not found");
        }
    }

    private void displayEmployees() {
        System.out.println("List of employees:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**Welcome to the Employee Management System**");
            System.out.println("1). Add Employee to the Database");
            System.out.println("2). Search for Employee");
            System.out.println("3). Edit Employee Details");
            System.out.println("4). Delete Employee Details");
            System.out.println("5). Display all Employees working in this company");
            System.out.println("6). EXIT");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ems.addEmployee(scanner);
                    break;
                case 2:
                    ems.searchEmployee(scanner);
                    break;
                case 3:
                    ems.editEmployee(scanner);
                    break;
                case 4:
                    ems.deleteEmployee(scanner);
                    break;
                case 5:
                    ems.displayEmployees();
                    break;
                case 6:
                    System.out.println("Thank you for using Employee Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
