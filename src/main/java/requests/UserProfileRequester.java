package requests;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;

import static io.restassured.RestAssured.given;

public class UserProfileRequester extends Request<BaseModel> {
    public UserProfileRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        throw new UnsupportedOperationException("POST method expected");
    }


    public ValidatableResponse put(BaseModel model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .put("/api/v1/customer/profile")
                .then()
                .spec(responseSpecification);
    }

    public ValidatableResponse get() {
        return given()
                .spec(requestSpecification)
                .get("/api/v1/customer/profile")
                .then()
                .spec(responseSpecification);
    }


}
