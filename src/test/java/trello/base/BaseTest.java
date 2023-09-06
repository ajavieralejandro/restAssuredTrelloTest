package trello.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static RequestSpecification badRequestSpec;
    protected static String boardID;


    @BeforeClass
    public static void createRequestSpecifications(){
        requestSpec=new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com")
                .addQueryParam("token","ATTA182c4085bb74bbdb35532a8451c8dd99094fa36dfd12646c601922c01f9b42faBD07069A")
                .addQueryParam("key","5daedec879ac842ab08d0b7d39d43e73")
                .addHeader("Content-Type","application/json")
                .build();
        badRequestSpec=new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com")
                .addQueryParam("token","asdasdasdasd")
                .addQueryParam("key","5asdasdasd")
                .addHeader("Content-Type","application/json")
                .build();
    }
   @AfterClass
    public static void deleteBoard(){
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body("")
                .when().delete("1/boards/{id}");

    }
    @DataProvider(name="BoardField")
    public Object [][] getData(){
        return new Object[][]{
                {"idMemberCreator"},
                {"idOrganization"},
                {"desc"},
                {"name"}
        };
    }

}
