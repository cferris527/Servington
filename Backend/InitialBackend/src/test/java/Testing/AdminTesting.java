package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.example.experiment1.Admin.Admin;
import com.example.experiment1.Organization.Organization;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//@RunWith(Parameterized.class)
public class AdminTesting {



    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
    }


    @Test
    public void test_listAdmin() throws Throwable {
        given().
                when().
                get("/listAdmins").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_createAdmin() throws JSONException {
        JSONObject admin  = new JSONObject();
        admin.put("username", "Admin1");
        admin.put("password", "password");

        given()
                .contentType(ContentType.JSON)
                .body(admin.toString())
                .log().all()

                .when()
                .post("http://localhost:8080/createAdmin")

                .then()
                .assertThat().statusCode(200)
                .log().all();

    }

    @Test
    public void test_checkingCreate() throws Throwable {
        given().
                when().
                get("/listAdmins").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body(
                        "username", hasItems("Admin1"),
                        "password", hasItems("password")

                );
    }

    @Test
    public void test_login() throws Throwable {
        given().
                when().
                get("/adminLogin/Admin1/password").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_deleteAdmin() throws JSONException {
        JSONObject admin  = new JSONObject();
        admin.put("id", "1");

        given()
                .contentType(ContentType.JSON)
                .body(admin.toString())
                .log().all()

                .when()
                .post("http://localhost:8080/deleteAdmin")

                .then()
                .assertThat().statusCode(200)
                .log().all();

    }


    @Test
    public void test_Admin() throws Throwable {
        Admin a = new Admin("please", "please");

        Assertions.assertEquals(a.getPassword(), "please");
        Assertions.assertEquals(a.getUsername(), "please");
    }




}
