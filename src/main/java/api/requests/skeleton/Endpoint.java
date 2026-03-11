package api.requests.skeleton;

import api.models.*;
import lombok.AllArgsConstructor;

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
    ),
    ACCOUNTS_TRANSFER(
            "/accounts/transfer",
            TransferRequest.class,
            TransferResponse.class
    ),
    ACCOUNTS_DEPOSIT(
            "/accounts/deposit",
            DepositRequest.class,
            AccountResponse.class //DepositResponse
    ),
    ACCOUNTS_TRANSACTION(
            "/accounts/{accountId}/transactions",
            TransactionRequest.class,
            TransactionsResponse.class // парсится в UserSteps вручную
    ),

    UPDATE_PROFILE(
            "/customer/profile",
            UpdateProfileRequest.class,
            UserProfileUpdateResponse.class //DepositResponse
    ),
    USER_PROFILE("/customer/profile",
            BaseModel.class,
            UserProfileResponse.class
    ),
    USER_GET_ACCOUNTS(
            "/customer/accounts",
            BaseModel.class,
            AccountResponse.class

    ), TRANSFER_WITH_FRAUD_CHECK(
            "/accounts/transfer-with-fraud-check",
            TransferRequest.class,
            TransferResponse.class
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
