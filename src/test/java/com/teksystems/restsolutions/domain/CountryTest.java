package com.teksystems.restsolutions.domain;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class CountryTest {
    final static Logger logger= Logger.getLogger(CountryTest.class);
    Country country;

    @BeforeSuite
    public void testBeforeSuite() {
    }

    @AfterSuite
    public void testAfterSuite() {
    }

    @BeforeTest
    public void testBeforeTest() {
      country = new Country();
      country.setName("italy");
      country.setCapital("Rome");
    }

    @Test(priority = 0)
    public void testName(){
        Assert.assertEquals("italy", country.getName());
        Assert.assertEquals("Rome",country.getCapital());
    }

    @AfterTest
    public void testAfterTest() {
    }
}
