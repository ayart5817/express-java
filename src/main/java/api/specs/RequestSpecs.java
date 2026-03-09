package api.specs;

import api.configs.Config;
import api.models.LoginUserRequest;
import api.requests.skeleton.Endpoint;
import api.requests.skeleton.requester.CrudRequester;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestSpecs {
    private static Map<String, String> authHeaders = new HashMap<>(Map.of("admin", "Basic YWRtaW46YWRtaW4="));
    private static final String DEFAULT_BASE_URI = Config.getProperty("server") + Config.getProperty("apiVersion");

    private RequestSpecs() {
    }

    private static RequestSpecBuilder defaultRequestBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilters(List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));
    }

    private static RequestSpecBuilder defaultRequestBuilder(String baseUri) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilters(List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()))
                .setBaseUri(baseUri);
    }


    public static RequestSpecification unauthSpec() {
        return unauthSpec(DEFAULT_BASE_URI);
    }

    // Новый метод с динамическим baseUri
    public static RequestSpecification unauthSpec(String baseUri) {
        return defaultRequestBuilder(baseUri).build();
    }


    public static RequestSpecification adminSpec() {
        return adminSpec(DEFAULT_BASE_URI);
    }

    // Новый метод с динамическим baseUri
    public static RequestSpecification adminSpec(String baseUri) {
        return defaultRequestBuilder(baseUri)
                .addHeader("Authorization", authHeaders.get("admin"))
                .build();
    }

    // Существующий метод для обратной совместимости
    public static RequestSpecification authAsUser(String username, String password) {
        return authAsUser(username, password, DEFAULT_BASE_URI);
    }

    // Новый метод с динамическим baseUri
    public static RequestSpecification authAsUser(String username, String password, String baseUri) {
        return defaultRequestBuilder(baseUri)
                .addHeader("Authorization", getUserAuthHeader(username, password, baseUri))
                .build();
    }


    public static String getUserAuthHeader(String username, String password) {
        return getUserAuthHeader(username, password, DEFAULT_BASE_URI);
    }

    public static String getUserAuthHeader(String username, String password, String baseUri) {
        String userAuthHeader;

        if (!authHeaders.containsKey(username)) {
            userAuthHeader = new CrudRequester(
                    RequestSpecs.unauthSpec(baseUri),  // используем динамический baseUri
                    Endpoint.LOGIN,
                    ResponseSpecs.requestReturnsOK())
                    .post(LoginUserRequest.builder().username(username).password(password).build())
                    .extract()
                    .header("Authorization");

            authHeaders.put(username, userAuthHeader);
        } else {
            userAuthHeader = authHeaders.get(username);
        }
        return userAuthHeader;
    }
}