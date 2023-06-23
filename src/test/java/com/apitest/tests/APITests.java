package com.apitest.tests;

import com.apitest.base.Base;
import com.apitest.endpoint.APIEndpoints;
import com.apitest.pojo.Employee;
import com.apitest.utilities.GenerateTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.given;

public class APITests extends Base {
    GenerateTestData gd = new GenerateTestData();
    public Integer id;
    @Test(priority = 0)
    public void test_GetAllEmployees(){

        Response response = given()
                .when()
                .get(APIEndpoints.LIST_ALL_EMPLOYEE)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .response();

        JsonPath jp = response.jsonPath();
        String username = jp.get("[0].first_name");
        Assert.assertEquals("Sebastian",username);
    }

    @Test(priority = 1)
    public void test_CreateEmployee(){

        Employee emp = new Employee();
        emp.setFirstName(gd.generateFirstname());
        emp.setLastName(gd.generateLastname());
        emp.setEmail(gd.generateEmail());

        Employee empresponse = given()
                .contentType(ContentType.JSON)
                .body(emp)
                .when()
                .post(APIEndpoints.CREATE_EMPLOYEE)
                .then()
                .statusCode(201)
                .extract()
                .as(Employee.class);

        id= empresponse.getId();
        assertThat(emp.getFirstName(),equalTo(gd.firstname));
    }
    @Test(priority = 2)
    public void test_deleteEmployee(){
        given()
                .when()
                .delete(APIEndpoints.DELETE_EMPLOYEE+id)
                .then()
                .statusCode(200);
    }
    @Test(priority = 3)
    public void test_getEmployee(){
        given()
                .when()
                .get(APIEndpoints.GET_EMPLOYEE+id)
                .then()
                .statusCode(404);
    }
}
