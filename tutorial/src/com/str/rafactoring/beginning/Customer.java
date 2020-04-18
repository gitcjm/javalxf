package com.str.rafactoring.beginning;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 客户租赁
    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    // 结算单
    public String statement() {
        // 页眉信息
        String result = "Rental Record for " + getName() + "\n";

        // 清单明细
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += "\t" + each.getMovie().getTitle() + "\t" + each.getCharge() + "\n";
        }

        // 页脚统计信息
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";

        return result;
    }

    // 计算总金额
    private double getTotalCharge() {
        double result = 0;

        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }

        return result;
    }

    // 计算总积分
    private int getTotalFrequentRenterPoints() {
        int result = 0;

        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }

        return result;
    }

    // html版结算单
    public String htmlStatement() {
        // 页眉信息
        String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><p>\n";

        // 结算单明细
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += "\t" + each.getMovie().getTitle() + "\t" + each.getCharge() + "<BR>\n";
        }

        // 页脚统计信息
        result += "<p>You owe <EM> " + getTotalCharge() + "</EM>\n";
        result += "<p>On this rental you earned <EM>" +
                getTotalFrequentRenterPoints() + "</EM> frequent renter points";

        return result;
    }
}
