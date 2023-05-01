package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

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
public class OrganizationTest {



    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
    }


    @Test
    public void test_listOrgs() throws Throwable {
        given().
                when().
                get("/listOrganizations").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_createOrg() throws JSONException {
        JSONObject admin  = new JSONObject();
        admin.put("username", "O");
        admin.put("password", "password");

        given()
                .contentType(ContentType.JSON)
                .body(admin.toString())
                .log().all()

                .when()
                .post("http://localhost:8080/createOrg")

                .then()
                .assertThat().statusCode(200)
                .log().all();

    }

    @Test
    public void test_checkingCreate() throws Throwable {
        given().
                when().
                get("/listOrganizations").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body(
                        "username", hasItems("O"),
                        "password", hasItems("password")

                );
    }

    @Test
    public void test_login() throws Throwable {
        given().
                when().
                get("/orgLogin/Orgy/Orgy").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_editFields() throws JSONException {
        JSONObject admin  = new JSONObject();
        admin.put("id", 2);
        admin.put("password", "pass");
        admin.put("displayName", "test");
        admin.put("email", "test");
        admin.put("phone_number", "test");



        given()
                .contentType(ContentType.JSON)
                .body(admin.toString())
                .log().all()

                .when()
                .post("http://localhost:8080/orgEditFields")

                .then()
                .assertThat().statusCode(200)
                .log().all();

    }

    @Test
    public void test_banOrg() throws Throwable {
        given().
                when().
                post("/banOrganization/Orgy").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_unbanOrg() throws Throwable {
        given().
                when().
                post("/unbanOrganization/Orgy").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_Org() throws Throwable {
        Organization o = new Organization("please", "please", "please", "please", "please","please");

        Assertions.assertEquals(o.getEmail(), "please");
        Assertions.assertEquals(o.getPassword(), "please");
        Assertions.assertEquals(o.getUsername(), "please");
        Assertions.assertEquals(o.getDisplayName(), "please");
        Assertions.assertEquals(o.getPhone_number(), "please");
    }






}
