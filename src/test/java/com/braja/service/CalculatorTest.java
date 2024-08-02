package com.braja.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTest {

    @BeforeClass
    public static void setupBeforeClass(){
        System.out.println("CalculatorTest::setupBeforeClass");
    }

    @AfterClass
    public static void setupAfterClass(){
        System.out.println("CalculatorTest::setupAfterClass");
    }

    @Before
    public void setupBefore(){
        System.out.println("CalculatorTest::setupBefore");
    }

    @After
    public void setupAfter(){
        System.out.println("CalculatorTest::setupAfter");
    }

    @Test
    public void multiplyTest(){
        System.out.println("CalculatorTest::multiplyTest");
        Assert.assertEquals(100, Calculator.multiply(10,10));
    }

    @Test
    public void divideTest(){
        System.out.println("CalculatorTest::divideTest");
        Assert.assertNotEquals(1, Calculator.multiply(10,10));
    }

}
