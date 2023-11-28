package trello.tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
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

    public void getListsOfCountries() throws IOException {
        XmlPath xmlPath;
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
                .statusCode(200)
                .extract().response();
        String stringResponse = response.asString();
        Assert.assertTrue(stringResponse.contains("Argentina"));


    }

    @Test(priority = 1)

    public void getCapitalofArgentina() throws IOException {
        XmlPath xmlPath;
        String xmlBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <CapitalCity xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\n" +
                "      <sCountryISOCode>AR</sCountryISOCode>\n" +
                "    </CapitalCity>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";

        Response response=given().spec(requestSOAP)
                .and()
                .body(xmlBody)
                .when()
                .post()
                .then()
                .and()
                .log().all()
                .statusCode(200)
                .extract().response();
        String stringResponse = response.asString();
        Assert.assertTrue(stringResponse.contains("Buenos Aires"));


    }

    @Test(priority = 2)

    public void getCurrenciOfArgentina() throws IOException {
        XmlPath xmlPath;
        String xmlBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <CountryCurrency xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\n" +
                "      <sCountryISOCode>AR</sCountryISOCode>\n" +
                "    </CountryCurrency>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";

        Response response=given().spec(requestSOAP)
                .and()
                .body(xmlBody)
                .when()
                .post()
                .then()
                .and()
                .log().all()
                .statusCode(200)
                .extract().response();
        String stringResponse = response.asString();
        Assert.assertTrue(stringResponse.contains("Pesos"));


    }
}
