package com.str.rafactoring.beginning;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerTest {
    List<Movie> movies = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        movies.add(new Movie("复仇者联盟4", Movie.NEW_RELEASE)); // 0
        movies.add(new Movie("寻梦环游记",Movie.CHILDRENS));     // 1
        movies.add(new Movie("小猪佩奇", Movie.CHILDRENS));      // 2
        movies.add(new Movie("星级巡航", Movie.REGULAR));        // 3
        movies.add(new Movie("少林寺", Movie.REGULAR));          // 4
        movies.add(new Movie("人在囧途", Movie.NEW_RELEASE));    // 5

        customers.add(new Customer("多多"));
        customers.add(new Customer("蛋蛋"));
        customers.add(new Customer("大燕"));

        // 多多租借了《寻梦环游记》和《小猪佩奇》
        customers.get(0).addRental(new Rental(movies.get(1), 3));
        customers.get(0).addRental(new Rental(movies.get(2), 4));
        // 蛋蛋租借了《复联4》和《星级巡航》
        customers.get(1).addRental(new Rental(movies.get(0), 2));
        customers.get(1).addRental(new Rental(movies.get(3), 1));
        // 大燕租借了《少林寺》和《人在囧途》
        customers.get(2).addRental(new Rental(movies.get(4), 2));
        customers.get(2).addRental(new Rental(movies.get(5), 3));
    }

    @Test
    public void statement() {
        // 多多的结算单
        System.out.println(customers.get(0).statement());
        // 蛋蛋的结算单
        System.out.println(customers.get(1).statement());
        // 大燕的结算单
        //System.out.println(customers.get(2).statement());
    }

    @Test
    public void htmlStatement() {
        System.out.println(customers.get(0).htmlStatement());
    }
}