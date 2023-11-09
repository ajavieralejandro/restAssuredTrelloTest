package trello.tests;

import com.google.gson.JsonObject;
import org.testng.annotations.Listeners;
import trello.base.*;
import org.testng.annotations.Test;
import trello.pojo.GetBoard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Listeners(trello.base.Listeners.class)
public class NegativeSenario extends BaseTest {

    private static String listID;
    private static String cardID;

    protected  String invalidID;
    GetBoard gb= new GetBoard();

    @Test(priority = 0)
    public void createBoard() {
        gb=given().spec(requestSpec)
                .queryParams("name", "Test Case 1","prefs_background","orange"
                        ,"prefs_background_url","https://images.unsplash.com/photo-1676694090990-b9e29828fdd3?ixid=Mnw3MDY2fDB8MXxjb2xsZWN0aW9ufDN8MzE3MDk5fHx8fHwyfHwxNjc2OTc4MzA4&ixlib=rb-4.0.3&w=2560&h=2048&q=90")
                .when().post("1/boards").
                then().log().all().assertThat().statusCode(200).extract().response().as(GetBoard.class);//.path("id");
        boardID = gb.getId();
        invalidID = "asdf";

    }

    @Test(priority = 0)
    public void createBoarWithNoName() {
        given().spec(requestSpec)
                .queryParams("prefs_background","orange"
                        ,"prefs_background_url","https://images.unsplash.com/photo-1676694090990-b9e29828fdd3?ixid=Mnw3MDY2fDB8MXxjb2xsZWN0aW9ufDN8MzE3MDk5fHx8fHwyfHwxNjc2OTc4MzA4&ixlib=rb-4.0.3&w=2560&h=2048&q=90")
                .when().post("1/boards").
                then().log().all().assertThat().statusCode(400).body("error",equalTo("ERROR"));

    }

    @Test(priority = 1)
    public void createBoardWithInvalidName() {
        String longString = "";
        int i;
        for(i=0;i<=16384;i++){
            longString+="a";
        }
        given().spec(requestSpec)
                .queryParams("name",longString,"prefs_background","orange"
                        ,"prefs_background_url","https://images.unsplash.com/photo-1676694090990-b9e29828fdd3?ixid=Mnw3MDY2fDB8MXxjb2xsZWN0aW9ufDN8MzE3MDk5fHx8fHwyfHwxNjc2OTc4MzA4&ixlib=rb-4.0.3&w=2560&h=2048&q=90")
                .when().post("1/boards").
                then().log().all().assertThat().statusCode(414);


    }

    @Test(priority = 1)
    public void UnauthorizedAccessCreateBoard() {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("name","Javitus Board");

        given().spec(badRequestSpec)
                .body(requestParams.toString())
                .when().post("1/boards").
                then().log().all().assertThat().statusCode(401);


    }

    @Test(dependsOnMethods = "createBoard",priority = 2)
    public void unathorizedAccesGetCreatedBoard() {

        given().spec(badRequestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(401);

    }

    @Test(dependsOnMethods = "createBoard",priority = 2)
    public void getCreatedBoardWithInvalidId() {
        String invalidID = "asdf1234";
        given().spec(requestSpec)
                .pathParam("id", invalidID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(404).toString().equals("invalid id");

    }


    @Test(dependsOnMethods = "createBoard",priority = 3,dataProvider = "BoardField")
    public void getAFieldOnABoard(String field) {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/"+field).
                then().log().all().assertThat().statusCode(200);

    }

    @Test(dependsOnMethods = "createBoard",priority = 3)
    public void updateBoardWithInvalidName() throws IOException {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("name",123);

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body(requestParams.toString())
                .when().put("1/boards/{id}")
                .then().log().body().assertThat().statusCode(400);


    }

    @Test(dependsOnMethods = "createBoard",priority = 4)
    public void updateBoardWithInvalidDesc() throws IOException {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("desc",123);

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body(requestParams.toString())
                .when().put("1/boards/{id}")
                .then().log()
                .body()
                .assertThat().statusCode(400);

    }

    @Test(dependsOnMethods = "createBoard",priority = 9)
    public void deleteBoardWithInvalidId() {
        given().spec(requestSpec)
                .pathParam("id", "asdf")
                .body("")
                .when().delete("1/boards/{id}").
                then().log().all().assertThat().statusCode(400);
    }

    @Test(dependsOnMethods = "createBoard",priority = 9)
    public void deleteBoardWithInvalidAuth() {

        given().spec(badRequestSpec)
                .pathParam("id", boardID)
                .body("")
                .when().delete("1/boards/{id}").
                then().log().all().assertThat().statusCode(400);
    }


    @Test(dependsOnMethods = "createBoard",priority = 6)
    public void createListWithInvalidId() {
        given().spec(requestSpec)
                .pathParam("id", "asdf1234")
                .queryParam("name","list1")
                .when().post("1/boards/{id}/lists").
                then().log().all().assertThat().statusCode(400);

    }

    @Test(dependsOnMethods = "createBoard",priority = 6)
    public void unathorizedCreateList() {
        given().spec(badRequestSpec)
                .pathParam("id", boardID)
                .queryParam("name","list1")
                .when().post("1/boards/{id}/lists").
                then().log().all().assertThat().statusCode(400);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void unathorizedGetListsOnTheBoard() {
        given().spec(badRequestSpec)
                .pathParam("id", boardID)
                .queryParam("name","list1")
                .when().get("1/boards/{id}/lists").
                then().log().all().assertThat().statusCode(400);

    }

    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void getListsOnTheBoardwithInvalidID() {
        given().spec(requestSpec)
                .pathParam("invalidID", invalidID)
                .queryParam("name","list1")
                .when().get("1/boards/{invalidID}/lists").
                then().log().all().assertThat().statusCode(400);

    }

    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void getClosedListsOnTheBoardwithInvalidID() {
        given().spec(requestSpec)
                .pathParam("invalidID", invalidID)
                .queryParam("name","list1")
                .when().get("1/boards/{invalidID}/lists/closed").
                then().log().all().assertThat().statusCode(400);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void unauthorizedGetClosedListsOnTheBoardwithInvalidID() {
        given().spec(badRequestSpec)
                .pathParam("invalidID", boardID)
                .queryParam("name","list1")
                .when().get("1/boards/{invalidID}/lists/closed").
                then().log().all().assertThat().statusCode(400);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void createLabelOnBoardWithInvalidID() {
        given().spec(requestSpec)
                .pathParam("id", invalidID)
                .queryParam("name","label1","color","blue")
                .when().post("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(400);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void unathorizedCreateLabelOnBoard() {
        given().spec(badRequestSpec)
                .pathParam("id", boardID)
                .queryParam("name","label1","color","blue")
                .when().post("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(400);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void createLabelOnBoardWithNoParams() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().post("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(400);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void createLabelOnBoardWithInvalidColor() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .queryParam("name","boardname","label1","color",1234)
                .when().post("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(400);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void createLabelOnBoardWithInvalidName() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .queryParam("name",123,"label1","color","asdasfda")
                .when().post("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(400);

    }


    @Test(dependsOnMethods = "createBoard",priority = 8)
    public void unathorizedGetListOfLabels() {
        given().spec(badRequestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(400);

    }

    @Test(dependsOnMethods = "createBoard",priority = 8)
    public void invalidBoardIDGetListOfLabels() {
        given().spec(badRequestSpec)
                .pathParam("id", invalidID)
                .when().get("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(400);

    }










    @Test(dependsOnMethods = "createBoard",priority = 9)
    public void deleteCreatedBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body("")
                .when().delete("1/boards/{id}").
                then().log().all().assertThat().statusCode(200);
    }
}
