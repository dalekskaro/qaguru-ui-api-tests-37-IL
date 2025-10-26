package specs;

import static helpers.CustomAllureListener.withCustomTemplates;
import static helpers.Url.BASE_URL;
import static io.restassured.RestAssured.with;
import io.restassured.builder.ResponseSpecBuilder;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DemoQaSpecification {

  public static RequestSpecification requestSpecification = with()
      .filter(withCustomTemplates())
      .log().uri()
      .log().body()
      .log().headers()
      .contentType(JSON)
      .baseUri(BASE_URL);

  public static ResponseSpecification responseSpecification(int code) {
    return new ResponseSpecBuilder()
        .log(STATUS)
        .log(BODY)
        .build()
        .statusCode(code);
  }
}
