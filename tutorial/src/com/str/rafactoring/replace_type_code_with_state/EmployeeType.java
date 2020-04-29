package com.str.rafactoring.replace_type_code_with_state;

public abstract class EmployeeType {

    abstract int getTypeCode();

    // employee type code
    protected static final int ENGINEER = 0;
    protected static final int SALESMAN = 1;
    protected static final int MANAGER = 2;
    protected static final int CEO = 3;

    static EmployeeType newType(int code) {
        switch (code) {
            case ENGINEER:
                return new Engineer();
            case SALESMAN:
                return new Salesman();
            case MANAGER:
                return new Manager();
            case CEO:
                return new Ceo();
            default:
                throw new IllegalArgumentException("Incorrect Employee code");
        }
    }

    abstract int payAmount(Employee emp);
}

class Engineer extends EmployeeType {
    @Override
    int getTypeCode() {
        return ENGINEER;
    }

    @Override
    int payAmount(Employee emp) {
        return emp.getMonthlySalary();
    }
}

class Salesman extends EmployeeType {
    @Override
    int getTypeCode() {
        return SALESMAN;
    }

    @Override
    int payAmount(Employee emp) {
        return emp.getMonthlySalary() + emp.getCommission();
    }
}

class Manager extends EmployeeType {
    @Override
    int getTypeCode() {
        return MANAGER;
    }

    @Override
    int payAmount(Employee emp) {
        return emp.getMonthlySalary() + emp.getBonus();
    }
}

class Ceo extends EmployeeType {

    @Override
    int getTypeCode() {
        return CEO;
    }

    @Override
    int payAmount(Employee emp) {
        return emp.getMonthlySalary() + emp.getCommission() + emp.getBonus();
    }
}
