package com.apitest.base;

import com.apitest.utilities.ExtentReportListeners;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(ExtentReportListeners.class)
public class Base {
    @BeforeClass
    public void setBaseURI(){
        RestAssured.baseURI="http://localhost:3000";
    }
}
