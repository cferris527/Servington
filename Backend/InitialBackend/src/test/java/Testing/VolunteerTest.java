package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//@RunWith(Parameterized.class)
public class VolunteerTest {



    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
    }


    @Test
    public void test_listVolunteers() throws Throwable {
        given().
                when().
                get("/listVolunteers").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_createVolunteer() throws JSONException {
        JSONObject admin  = new JSONObject();
        admin.put("username", "V");
        admin.put("password", "password");

        given()
                .contentType(ContentType.JSON)
                .body(admin.toString())
                .log().all()

                .when()
                .post("http://localhost:8080/createVolunteer")

                .then()
                .assertThat().statusCode(200)
                .log().all();

    }

    @Test
    public void test_checkingCreate() throws Throwable {
        given().
                when().
                get("/listVolunteers").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body(
                        "username", hasItems("V"),
                        "password", hasItems("password")

                );
    }

    @Test
    public void test_login() throws Throwable {
        given().
                when().
                get("/volunteerLogin/V/password").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }






}
