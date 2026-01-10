package requests.steps;

import lombok.Value;
import models.CreateUserRequest;
import models.CreateUserResponse;

@Value
public class CreatedUser {
    CreateUserRequest request;   // содержит исходный пароль
    CreateUserResponse response; // содержит id, accounts и т.д.

}
