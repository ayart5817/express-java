package api.requests.skeleton.interfaces;

import io.restassured.response.ValidatableResponse;
import api.models.BaseModel;

public interface CrudEndpointInterface {
    ValidatableResponse post(BaseModel model);
    ValidatableResponse get(Long id);
    ValidatableResponse update(long id, BaseModel model);
    ValidatableResponse delete(long id);
    ValidatableResponse put(BaseModel model); // ← только если нужен
}
