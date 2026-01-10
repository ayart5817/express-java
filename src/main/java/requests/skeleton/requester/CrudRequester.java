package requests.skeleton.requester;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;
import requests.skeleton.Endpoint;
import requests.skeleton.HttpRequest;
import requests.skeleton.interfaces.CrudEndpointInterface;

import static io.restassured.RestAssured.given;

public class CrudRequester extends HttpRequest implements CrudEndpointInterface {

    public CrudRequester(RequestSpecification requestSpecification, Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        var body = model == null ? "" : model;
        return given()
                .spec(requestSpecification)
                .body(body)
                .post(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse get(Long id) {
        return given()
                .spec(requestSpecification)
                .get(endpoint.getUrl(), id)
                .then()
                .assertThat()
                .spec(responseSpecification);
    }


    @Override
    public ValidatableResponse update(long id, BaseModel model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .put(endpoint.getUrl(), id)
                .then()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse delete(long id) {
        return given()
                .spec(requestSpecification)
                .delete(endpoint.getUrl(), id)
                .then()
                .spec(responseSpecification);
    }

    public ValidatableResponse put(BaseModel model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .put(endpoint.getUrl())
                .then()
                .spec(responseSpecification);
    }

    public ValidatableResponse get() {
        return given()
                .spec(requestSpecification)
                .get(endpoint.getUrl())
                .then()
                .spec(responseSpecification);
    }

}
