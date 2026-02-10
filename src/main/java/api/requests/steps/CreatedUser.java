package api.requests.steps;

import lombok.Value;
import api.models.CreateUserRequest;
import api.models.CreateUserResponse;

@Value
public class CreatedUser {
    CreateUserRequest request;   // содержит исходный пароль
    CreateUserResponse response; // содержит id, accounts и т.д.

}
