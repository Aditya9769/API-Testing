package com.apitest.utilities;

import com.github.javafaker.Faker;

public class GenerateTestData {
    Faker faker = new Faker();
    public String firstname;
    public String lastname;
    public String generateFirstname(){
        firstname = faker.name().firstName();
        return firstname;
    }

    public String generateLastname(){
        lastname = faker.name().lastName();
        return lastname;
    }

    public String generateEmail(){
        return firstname+"@codingthesmartway.com";
    }
}
