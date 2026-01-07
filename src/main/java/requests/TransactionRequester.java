package requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.DepositRequest;

import static io.restassured.RestAssured.given;

public class TransactionRequester extends Request<DepositRequest> {
    private final int accountId;

    public TransactionRequester(RequestSpecification requestSpec,
                                ResponseSpecification responseSpec,
                                int accountId) {
        super(requestSpec, responseSpec);
        this.accountId = accountId;
    }

    @Override
    public ValidatableResponse post(DepositRequest model) {
        throw new UnsupportedOperationException("GET method expected");
    }

    public ValidatableResponse get() {
        return given()
                .spec(requestSpecification)
                .get("/api/v1/accounts/" + accountId + "/transactions")
                .then()
                .spec(responseSpecification);
    }
}
