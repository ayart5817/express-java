package requests.steps;

import models.UpdateProfileRequest;
import models.UserProfileResponse;
import models.UserProfileUpdateResponse;
import requests.skeleton.Endpoint;
import requests.skeleton.requester.CrudRequester;
import requests.skeleton.requester.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class ProfileSteps {
    public static UserProfileResponse getProfile(String username, String password) {

        return new ValidatedCrudRequester<UserProfileResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.USER_PROFILE,
                ResponseSpecs.requestReturnsOK()).getAndExtract();

    }

    public static UserProfileUpdateResponse updateProfile(
            String username, String password, String newName) {
        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();

        return new ValidatedCrudRequester<UserProfileUpdateResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsOK()
        ).putAndExtract(request);
    }

    // Негативное обновление — возвращает строку ошибки
    public static String updateProfileFails(
            String username, String password, String newName) {
        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();

        return new CrudRequester(
                RequestSpecs.authAsUser(username, password),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.depositRejectedPlainText()
        ).put(request)
                .extract()
                .asString();
    }
}
