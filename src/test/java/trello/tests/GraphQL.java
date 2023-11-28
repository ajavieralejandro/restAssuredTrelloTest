package trello.tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import trello.base.BaseTest;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.*;


@Listeners(trello.base.Listeners.class)
public class GraphQL extends BaseTest {

    @DataProvider
    public Object[][] getQueryData(){
        return new Object[][] {{"8"},
        };
    }

    @Test
    public void getCompanyData_checkCeo_shouldBeElonMusk() {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("{ company { name ceo coo } }");

        given().
                contentType(ContentType.JSON).
                body(query).
                when().
                post("https://main--spacex-l4uc6p.apollographos.net/graphql").
                then().
                assertThat().
                statusCode(200).
                and().
                body("data.company.ceo", equalTo("Elon Musk"));
    }

    @Test
    //Nonne mission
    public void getAllMissionsEmpty() {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("{ missions { description id  } }");

        given().
                contentType(ContentType.JSON).
                body(query).
                when().
                post("https://main--spacex-l4uc6p.apollographos.net/graphql").
                then().
                assertThat().
                statusCode(200).
                and().
                body("data.missions", hasSize(0))   ; }

    @Test
    //Nonne mission
    public void getAllRockets() {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("{ rockets { id name  } }");

        given().
                contentType(ContentType.JSON).
                body(query).
                when().
                post("https://main--spacex-l4uc6p.apollographos.net/graphql").
                then().
                assertThat().
                statusCode(200).
                and().
                body("data.rockets", hasSize(4))   ; }


    @Test
    //Nonne mission
    public void getAllCapsules() {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("{ capsules { id type  } }");

        given().
                contentType(ContentType.JSON).
                body(query).
                when().
                post("https://main--spacex-l4uc6p.apollographos.net/graphql").
                then().
                assertThat().
                statusCode(200).
                and().
                body("data.capsules[0].type", equalTo("Dragon 1.0"))   ; }


}
