package Testing;
import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Team.Team;
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

public class TeamTest {
    @Before
    public void setUp(){
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testCreateTeam() throws Throwable{

        given()
                .when()
                .pathParam("orgID", 1)
                .pathParam("teamName", "testTeamName")
                .post("http://localhost:8080/createTeam/{orgID}/{teamName}")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void testDeleteTeamID() throws Throwable{
        given()
                .when()
                .pathParam("teamID", 10)
                .post("http://localhost:8080/deleteTeam/{teamID}")
                .then()
                .assertThat().statusCode(200);
    }

    @Test public void testListTeams() throws Throwable{

        given().
                when().
                get("/listTeams").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test public void testAddVolunteerTeam() throws Throwable{
        given()
                .when()
                .pathParam("teamID", 1)
                .pathParam("volunteerID", 1)
                .post("http://localhost:8080/addVolunteerTeam/{teamID}/{volunteerID}")
                .then()
                .assertThat().statusCode(200);
    }

    @Test public void testRemoveVolunteerTeam() throws Throwable{
        given()
                .when()
                .pathParam("teamID", 1)
                .pathParam("volunteerID", 1)
                .post("http://localhost:8080/removeVolunteerTeam/{teamID}/{volunteerID}")
                .then()
                .assertThat().statusCode(200);
    }

    @Test public void testGetVolunteerListTeam() throws Throwable{

        given().
                when().
                pathParam("teamID",1).
                get("http://localhost:8080/getVolunteersFromTeam/{teamID}").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test public void test_team() throws Throwable{
        Organization o = new Organization();
        Team t = new Team(o,"testTeamName");
        Assertions.assertEquals(t.getOrganization(), o);
        Assertions.assertEquals(t.getName(), "testTeamName");
    }



}
