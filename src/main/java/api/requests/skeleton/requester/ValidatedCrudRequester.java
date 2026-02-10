package api.requests.skeleton.requester;

import api.requests.skeleton.interfaces.GetAllEndpointInterface;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.models.BaseModel;
import api.requests.skeleton.Endpoint;
import api.requests.skeleton.HttpRequest;
import api.requests.skeleton.interfaces.CrudEndpointInterface;

import java.util.Arrays;
import java.util.List;

public class ValidatedCrudRequester<T extends BaseModel> extends HttpRequest implements CrudEndpointInterface, GetAllEndpointInterface {
    private CrudRequester crudRequester;

    public ValidatedCrudRequester(RequestSpecification requestSpecification,
                                  Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
        this.crudRequester = new CrudRequester(
                requestSpecification,
                endpoint,
                responseSpecification
        );
    }


    @Override

    public ValidatableResponse post(BaseModel model) {
        return crudRequester.post(model);
    }

    @Override

    public ValidatableResponse get(Long id) {
        return crudRequester.get(id);
    }

    @Override

    public ValidatableResponse update(long id, BaseModel model) {
        return crudRequester.update(id, model);
    }

    @Override

    public ValidatableResponse delete(long id) {
        return crudRequester.delete(id);
    }


    public ValidatableResponse put(BaseModel model) {
        return crudRequester.put(model);
    }

    @SuppressWarnings("unchecked")
    public T postAndExtract(BaseModel model) {
        return (T) post(model).extract().as(endpoint.getResponseModel());
    }

    @SuppressWarnings("unchecked")
    public T getAndExtract(Long id) {
        return (T) get(id).extract().as(endpoint.getResponseModel());
    }

    @SuppressWarnings("unchecked")
    public T getAndExtract() {
        return (T) crudRequester.get().extract().as(endpoint.getResponseModel());
    }


    @SuppressWarnings("unchecked")
    public T updateAndExtract(long id, BaseModel model) {
        return (T) update(id, model).extract().as(endpoint.getResponseModel());
    }

    @SuppressWarnings("unchecked")
    public T deleteAndExtract(long id) {
        return (T) delete(id).extract().as(endpoint.getResponseModel());
    }

    @SuppressWarnings("unchecked")
    public T putAndExtract(BaseModel model) {
        return (T) crudRequester.put(model).extract().as(endpoint.getResponseModel());
    }

    @Override
    public List<T> getAll(Class<?> clazz) {
      T[] array = (T[]) crudRequester.getAll(clazz).extract().as(clazz);
      return Arrays.asList(array);
    }
}
