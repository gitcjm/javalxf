package com.str.designpatterns.ChainOfResponsibility;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class HandlerChainTest {

    private HandlerChain chain = new HandlerChain();

    @Before
    public void setUp() throws Exception {
        chain.addHandler(new ManagerHandler());
        chain.addHandler(new DirectorHandler());
        chain.addHandler(new CEOHandler());
    }

    @Test
    public void process() {
        chain.process(new Request("Bob", new BigDecimal("123.45")));
        chain.process(new Request("Alice", new BigDecimal("1234.50")));
        chain.process(new Request("Bill", new BigDecimal("12345.60")));
        chain.process(new Request("Trump", new BigDecimal("12345.60")));
        chain.process(new Request("John", new BigDecimal("12345678.00")));
    }
}