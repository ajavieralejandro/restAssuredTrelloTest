package trello.tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import trello.base.BaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@Listeners(trello.base.Listeners.class)
public class SoapAPI extends BaseTest {

    @Test(priority = 0)

    public void RESTAssuredSOAPCall() throws IOException {
        String xmlBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <ListOfCountryNamesByName xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\n" +
                "    </ListOfCountryNamesByName>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";

        Response response=given().spec(requestSOAP)
                .and()
                .body(xmlBody)
                .when()
                .post()
                .then()
                .and()
                .log().all()
                .extract().response();

        System.out.println(re   sponse);
    }
}
