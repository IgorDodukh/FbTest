package settings;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

public class Specification {

    public static final ResponseSpecification postBodyResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody((allOf(
                    containsString("created_time"),
                    containsString("message"),
                    containsString("id"))))
            .build();

    public static final ResponseSpecification successResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(containsString("success"))
            .build();


}
