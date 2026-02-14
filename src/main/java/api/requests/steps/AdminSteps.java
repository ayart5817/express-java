package api.requests.steps;

import api.generators.RandomModelGenerator;
import api.models.CreateUserRequest;
import api.models.CreateUserResponse;
import api.requests.skeleton.Endpoint;
import api.requests.skeleton.requester.ValidatedCrudRequester;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;

import java.util.List;

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

    public static List<CreateUserResponse> getAllUsers() {
        return new ValidatedCrudRequester<CreateUserResponse>(
                RequestSpecs.adminSpec(),
                Endpoint.ADMIN_USER,
                ResponseSpecs.requestReturnsOK()).getAll(CreateUserResponse[].class);

    }

}