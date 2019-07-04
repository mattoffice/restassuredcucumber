package steps;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;

public class BDDStyledMethod {

    public static void SimpleGETPost(String postNumber) {

        given().contentType(ContentType.JSON).
                when().get(String.format("http://localhost:3000/posts/%s", postNumber)).
                then().body("author", hasItem("elliott"));
    }

    public static void ContainsInCollection() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/posts/")
                .then().body("author", hasItem("elliott"))
                .statusCode(200);
    }

    public static void PerformPathParameterGet() {

        given()
                .contentType(ContentType.JSON).
                with()
                .pathParams("post", 1).
                when()
                .get("http://localhost:3000/posts/{post}").
                then()
                .body("author", containsString("elliott"));
    }

    public static void PerformQueryParameterGet() {

        given()
                .contentType(ContentType.JSON).
                with()
                .queryParam("id", "3").
                when()
                .get("http://localhost:3000/posts/").
                then()
                .body("author", hasItem("lennon"));
    }

    public static void PerformPOSTWithBodyParameter() {
        HashMap<String,String> postContent = new HashMap<String,String>();
        postContent.put("id", "16");
        postContent.put("title", "steve");
        postContent.put("author", "jsdjhsj");

        given()
                .contentType(ContentType.JSON).
                with()
                .body(postContent).
                when()
                .post("http://localhost:3000/posts/").
                then()
                .body("author", Is.is("steve"));
    }
}
