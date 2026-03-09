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
        return createUser(null);
    }

    // Новый метод с динамическим baseUrl
    public static CreatedUser createUser(String baseUrl) {
        CreateUserRequest request = RandomModelGenerator.generate(CreateUserRequest.class);

        ValidatedCrudRequester<CreateUserResponse> requester;
        if (baseUrl != null && !baseUrl.isEmpty()) {
            requester = new ValidatedCrudRequester<CreateUserResponse>(
                    RequestSpecs.adminSpec(baseUrl),
                    Endpoint.ADMIN_USER,
                    ResponseSpecs.entityWasCreated()
            );
        } else {
            requester = new ValidatedCrudRequester<CreateUserResponse>(
                    RequestSpecs.adminSpec(),
                    Endpoint.ADMIN_USER,
                    ResponseSpecs.entityWasCreated()
            );
        }

        CreateUserResponse response = requester.postAndExtract(request);
        return new CreatedUser(request, response);
    }


    public static List<CreateUserResponse> getAllUsers() {
        return getAllUsers(null);
    }

    // Новый метод с динамическим baseUrl
    public static List<CreateUserResponse> getAllUsers(String baseUrl) {
        ValidatedCrudRequester<CreateUserResponse> requester;
        if (baseUrl != null && !baseUrl.isEmpty()) {
            requester = new ValidatedCrudRequester<CreateUserResponse>(
                    RequestSpecs.adminSpec(baseUrl),
                    Endpoint.ADMIN_USER,
                    ResponseSpecs.requestReturnsOK()
            );
        } else {
            requester = new ValidatedCrudRequester<CreateUserResponse>(
                    RequestSpecs.adminSpec(),
                    Endpoint.ADMIN_USER,
                    ResponseSpecs.requestReturnsOK()
            );
        }

        return requester.getAll(CreateUserResponse[].class);
    }
}