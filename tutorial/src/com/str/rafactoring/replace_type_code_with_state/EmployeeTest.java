package com.str.rafactoring.replace_type_code_with_state;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee engineer = new Employee(EmployeeType.ENGINEER);
        System.out.println("Engineer salary: " + engineer.payAmount());
        Employee salesman = new Employee(1);
        System.out.println("Salesman salary: " + salesman.payAmount());
        Employee manager = new Employee(2);
        System.out.println("Manager salary: " + manager.payAmount());
        Employee ceo = new Employee(EmployeeType.CEO);
        System.out.println("CEO salary: " + ceo.payAmount());
    }
}
