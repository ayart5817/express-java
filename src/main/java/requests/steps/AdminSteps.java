package requests.steps;

import generators.RandomModelGenerator;
import models.CreateUserRequest;
import models.CreateUserResponse;
import requests.skeleton.Endpoint;
import requests.skeleton.requester.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class AdminSteps {
    public static CreatedUser createUser() {
        CreateUserRequest request = RandomModelGenerator.generate(CreateUserRequest.class);
        CreateUserResponse response = new ValidatedCrudRequester<CreateUserResponse>(
                RequestSpecs.adminSpec(),
                Endpoint.ADMIN_USER,
                ResponseSpecs.entityWasCreated()
        ).postAndExtract(request);

        return new CreatedUser(request, response);
    }

}