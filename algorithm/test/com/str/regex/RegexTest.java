package com.str.regex;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegexTest {
    RegularExpression regex = new RegularExpression();

    @Test
    public void groupMatch() {
        //regex.groupMatch("0359-2081027");
        regex.groupMatch("022-356789");
    }

    @Test
    public void notGreedyMatch() {
        regex.notGreedyMatch("123000");
    }

    @Test
    public void getDecimalTailZero() {
        regex.getDecimalTailZero("123.4000");
    }
}