package Testing;

import com.example.experiment1.Report.Report;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import io.restassured.RestAssured;
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
import org.springframework.test.context.TestPropertySource;

public class ReportTest {

    @Before
    public void setUp(){
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testListReports() throws Throwable{
        given().
                when().
                get("/listReports").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void testGetReportWithParam() throws JSONException{
        Response reportResponse = given().
                // baseUri("http://localhost:8080").
                        contentType(ContentType.JSON).
                pathParam("id", "1").
                when().
                get("/getReport/{id}").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response();

        JsonPath jsonPathObj = reportResponse.jsonPath();
        Assertions.assertEquals(jsonPathObj.getLong("id"), 1);
        Assertions.assertEquals(jsonPathObj.getString("username"), "John");
        Assertions.assertEquals(jsonPathObj.getString("reportDescription"), "This post contains explicit content");
    }

    @Test
    public void testGetReportWithPostParam() throws JSONException{
        Response reportResponse = given().
                // baseUri("http://localhost:8080").
                        contentType(ContentType.JSON).
                pathParam("reportID", "1").
                when().
                get("/getPostFromReport/{reportID}").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response();

        JsonPath jsonPathObj = reportResponse.jsonPath();
        Assertions.assertEquals(jsonPathObj.getString("title"), "test");
    }


    @Test
    public void testCreateReport() throws Throwable{
        JSONObject reportParams = new JSONObject();
        reportParams.put("username", "testUsername");
        reportParams.put("title", "testTitle");
        reportParams.put("reportDescription", "testDescription");

        given()
                .contentType(ContentType.JSON)
                .body(reportParams.toString())
                .log().all()
                .when()
                .pathParam("postTitle", "test")
                .post("http://localhost:8080/createReport/{postTitle}")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void testDeleteReport() throws Throwable{
        given().
                when().
                pathParam("reportID", 1).
                post("/deleteReport/{reportID}").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_report() throws Throwable{
        Report r = new Report("testUsername", "testTitle", "testDescription");
        Assertions.assertEquals(r.getUsername(), "testUsername");
        Assertions.assertEquals(r.getTitle(), "testTitle");
        Assertions.assertEquals(r.getReportDescription(), "testDescription");
    }



}
