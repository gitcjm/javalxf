package com.str.rafactoring.replace_type_code_with_subclasses;

public abstract class Employee {

    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    abstract int getType();

    static Employee create(int type) {
        switch (type) {
            case ENGINEER:
                return new Engineer();
            case SALESMAN:
                return new Salesman();
            case MANAGER:
                return new Manager();
            default:
                throw new IllegalArgumentException("Incorrect type code value");
        }
    }

    public static void main(String[] args) {
        Employee engineer = Employee.create(Employee.ENGINEER);
        System.out.println(engineer.getType());
        Employee manager = Employee.create(Employee.MANAGER);
        System.out.println(manager.getType());
    }
}

class Engineer extends Employee {
    @Override
    public int getType() {
        return Employee.ENGINEER;
    }
}

class Salesman extends Employee {
    @Override
    public int getType() {
        return Employee.SALESMAN;
    }
}

class Manager extends Employee {
    @Override
    public int getType() {
        return Employee.MANAGER;
    }
}





