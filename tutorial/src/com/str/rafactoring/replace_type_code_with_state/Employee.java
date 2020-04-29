package com.str.rafactoring.replace_type_code_with_state;

public class Employee {

    private EmployeeType type;

    Employee (int code) {
        setType(code);
    }

    int getType() {
        return type.getTypeCode();
    }

    void setType(int arg) {
        this.type = EmployeeType.newType(arg);
    }

    int payAmount() {
        return type.payAmount(this);
    }

    // employee salary
    private int monthlySalary = 5000;
    private int commission = 600;       // 提成
    private int bonus = 500;            // 奖金

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public int getCommission() {
        return commission;
    }

    public int getBonus() {
        return bonus;
    }
}
