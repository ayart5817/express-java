package requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.TransferRequest;

import static io.restassured.RestAssured.given;


public class TransferRequester extends Request<TransferRequest> {
    public TransferRequester(RequestSpecification reqSpec, ResponseSpecification resSpec) {
        super(reqSpec, resSpec);
    }

    @Override
    public ValidatableResponse post(TransferRequest model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .post("/api/v1/accounts/transfer")
                .then()
                .spec(responseSpecification);
    }

    // получить строку (ошибок)
    public String postAndGetBodyAsString(TransferRequest model) {
        return post(model).extract().body().asString();
    }
}