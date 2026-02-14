package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

public class ResponseSpecs {
    private ResponseSpecs() {
    }

    private static ResponseSpecBuilder defaultResponseBuilder() {
        return new ResponseSpecBuilder();

    }

    public static ResponseSpecification entityWasCreated() {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_CREATED)
                .build();
    }

    public static ResponseSpecification requestReturnsOK() {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static ResponseSpecification requestReturnsBadRequest(String errorKey, String errorValue) {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectBody(errorKey, hasItem(errorValue))
                .build();
    }

    public static ResponseSpecification depositAccepted() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectBody("balance", notNullValue())
                .expectBody("transactions", notNullValue())
                .build();
    }

    public static ResponseSpecification depositRejected(String messagePart) {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectBody("message", containsString(messagePart))
                .build();
    }

    public static ResponseSpecification forbiddenAccess() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_FORBIDDEN)
                .build();
    }

    public static ResponseSpecification depositCreated() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_CREATED)
                .expectBody("balance", equalTo(0.0F))
                .expectBody("transactions", Matchers.empty())
                .build();
    }

    public static ResponseSpecification depositRejectedPlainText() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }

    public static ResponseSpecification transactionsAccounts() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static ResponseSpecification transferRejectedPlainText() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }

    public static ResponseSpecification transferAccepted() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

}
