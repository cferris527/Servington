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
import io.restassured.path.json.JsonPath;
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
public class PostTest {



    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void test_allPosts() throws Throwable {
        given().
                // baseUri("http://localhost:8080").
                when().
                get("/allPosts").
                then().
                log().all().
                assertThat().
                statusCode(200);


    }

    @Test
    public void testgetPostByTitle() {
        Response postResponse = given().
                // baseUri("http://localhost:8080").
                        contentType(ContentType.JSON).
                pathParam("title", "test").
                when().
                get("/getPostByTitle/{title}").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response();

        JsonPath jsonPathObj = postResponse.jsonPath();
        Assertions.assertEquals(jsonPathObj.getString("title"), "test");

    }


    @Test
    public void testgetPostByKeyword() {
       given().
                // baseUri("http://localhost:8080").
                        contentType(ContentType.JSON).
                pathParam("keyword", "help").
                when().
                get("/postDescriptionKeyword/{keyword}").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("title", hasItems("test2")
                );


    }

    @Test
    public void test_createPost() throws Throwable {
        JSONObject postParams = new JSONObject();
        postParams.put("title", "testingplease");


        Response postResponse = given().
                // baseUri("http://localhost:8080").
                        contentType(ContentType.JSON).
                body(postParams.toString()).
                pathParam("id", 1).
                when().
                post("/createPost/{id}").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response();
    }

    @Test
    public void test_addVolunteer() throws Throwable {


        Response postResponse = given().
                // baseUri("http://localhost:8080").
                        contentType(ContentType.JSON).
                pathParam("volunteerId", 1).
                pathParam("postTitle", "test").
                when().
                post("/addVolunteer/{postTitle}/{volunteerId}").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response();
    }

    @Test
    public void test_removeVolunteer() throws Throwable {


        Response postResponse = given().
                // baseUri("http://localhost:8080").
                        contentType(ContentType.JSON).
                pathParam("volunteerId", 1).
                pathParam("postTitle", "test").
                when().
                post("/removeVolunteer/{postTitle}/{volunteerId}").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response();
    }



}

