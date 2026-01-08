package requests.skeleton;

import lombok.AllArgsConstructor;
import models.*;

@AllArgsConstructor
public enum Endpoint {

    ADMIN_USER(
            "/admin/users",
            CreateUserRequest.class,
            CreateUserResponse.class
    ),
    LOGIN(
            "/auth/login",
            LoginUserRequest.class,
            LoginUserResponse.class
    ),
    ACCOUNTS(
            "/accounts",
            BaseModel.class,
            AccountResponse.class
    );


    public String getUrl() {
        return url;
    }

    public Class<? extends BaseModel> getRequestModel() {
        return requestModel;
    }

    public Class<? extends BaseModel> getResponseModel() {
        return responseModel;
    }

    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;

}
