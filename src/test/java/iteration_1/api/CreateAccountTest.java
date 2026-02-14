package iteration_1.api;

import api.models.CreateUserRequest;
import org.junit.jupiter.api.Test;
import api.requests.skeleton.Endpoint;
import api.requests.skeleton.requester.CrudRequester;
import api.requests.steps.AdminSteps;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;

public class CreateAccountTest extends BaseTest {


    @Test
    public void userCanCreateAccountTest() {
        CreateUserRequest userRequest = AdminSteps.createUser().getRequest();

        new CrudRequester(RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated())
                .post(null);
        // запросить все аккаунты пользователя и проверить, что наш аккаунт там


    }
}