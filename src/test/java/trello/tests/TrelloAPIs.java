package trello.tests;

import com.google.gson.JsonObject;
import org.testng.annotations.Listeners;
import trello.base.BaseTest;
import org.testng.annotations.Test;
import trello.pojo.GetBoard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Listeners(trello.base.Listeners.class)
public class TrelloAPIs extends BaseTest {

private static String listID;
private static String cardID;
GetBoard gb= new GetBoard();

    @Test(priority = 0)
    public void createBoard() {
         gb=given().spec(requestSpec)
                .queryParams("name", "Test Case 1","prefs_background","orange"
                        ,"prefs_background_url","https://images.unsplash.com/photo-1676694090990-b9e29828fdd3?ixid=Mnw3MDY2fDB8MXxjb2xsZWN0aW9ufDN8MzE3MDk5fHx8fHwyfHwxNjc2OTc4MzA4&ixlib=rb-4.0.3&w=2560&h=2048&q=90")
                .when().post("1/boards").
                then().log().all().assertThat().statusCode(200).extract().response().as(GetBoard.class);//.path("id");
        boardID = gb.getId();
    }

    @Test(dependsOnMethods = "createBoard",priority = 1)
    public void getCreatedBoard() {

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(200)
                .body("id", equalTo(boardID)).body("name",equalTo("Test Case 1"));

    }

    @Test(dependsOnMethods = "createBoard",priority = 2)
    public void getMembershipsOfABoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/memberships").
                then().log().all().assertThat().statusCode(200).body("[0].memberType", equalTo("admin"));

    }

    @Test(dependsOnMethods = "createBoard",priority = 3,dataProvider = "BoardField")
    public void getAFieldOnABoard(String field) {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/"+field).
                then().log().all().assertThat().statusCode(200);

    }

    @Test(dependsOnMethods = "createBoard",priority = 4)
    public void updateBoardName() throws IOException {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("name","Javitus Board");

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body(requestParams.toString())
                .when().put("1/boards/{id}")
                .then().log().body().assertThat().statusCode(200);


    }

    @Test(dependsOnMethods = "createBoard",priority = 4)
    public void updateBoardDesc() throws IOException {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("desc","Nuevo Board de Javier");

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body(requestParams.toString())
                .when().put("1/boards/{id}")
                .then().log()
                                .body()
                                .assertThat().statusCode(200)
                .body("desc",equalTo("Nuevo Board de Javier"));

    }

    @Test(dependsOnMethods = "createBoard",priority = 4)
    public void updateBoardVariousFields() throws IOException {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("desc","Nuevo Board de Javier2");
        requestParams.addProperty("name","Board de Javier2");
        requestParams.addProperty("closed","false");



        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body(requestParams.toString())
                .when().put("1/boards/{id}")
                .then().log()
                .body()
                .assertThat().statusCode(200)
                .body("desc",equalTo("Nuevo Board de Javier2"));

    }

    @Test(dependsOnMethods = "createBoard",priority = 4)
    public void updateBoardWithNoFields() throws IOException {
        JsonObject requestParams = new JsonObject();




        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body(requestParams.toString())
                .when().put("1/boards/{id}")
                .then().log()
                .body()
                .assertThat().statusCode(200)
                .body("desc",equalTo("Nuevo Board de Javier2"));

    }

    @Test(dependsOnMethods = "updateBoardWithNoFields",priority = 5)
    public void getUpdatedBoard() {

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(200)
                .body("id", equalTo(boardID)).body("name",equalTo("Board de Javier2"));

    }

    @Test(dependsOnMethods = "getUpdatedBoard",priority = 6)
    public void getBoardDesc() {

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/desc").
                then().log().all().assertThat().statusCode(200)
                .body("_value",equalTo("Nuevo Board de Javier2"));

    }


    @Test(dependsOnMethods = "createBoard",priority = 6)
    public void createList() {
       given().spec(requestSpec)
                .pathParam("id", boardID)
                .queryParam("name","list1")
                .when().post("1/boards/{id}/lists").
                then().log().all().assertThat().statusCode(200);

    }

    @Test(dependsOnMethods = "createList",priority = 7)
    public void getListsOnTheBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .queryParam("name","list1")
                .when().get("1/boards/{id}/lists").
                then().log().all().assertThat().statusCode(200);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void createLabelOnBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .queryParam("name","label1","color","blue")
                .when().post("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(200);

    }



    @Test(dependsOnMethods = "createLabelOnBoard",priority = 8)
    public void getCreatedLabelsOnBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/labels").
                then().log().all().assertThat().statusCode(200);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void getCardsOnBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/cards").
                then().log().all().assertThat().statusCode(200);

    }


    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void getListsOfCards() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .queryParam("cards","all")
                .when().get("1/boards/{id}/cards").
                then().log().all().assertThat().statusCode(200);

    }



/*
    @Test(dependsOnMethods = "createBoard",priority = 8)
    public void getCreatedBoardFail() {

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(201)
                .body("id", equalTo(boardID));
    }
    */


    @Test(dependsOnMethods = "createBoard",priority = 9)
    public void deleteCreatedBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body("")
                .when().delete("1/boards/{id}").
                then().log().all().assertThat().statusCode(200);
    }
}
