package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.LoginUserRequest;
import requests.LoginUserRequester;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static specs.ResponseSpecs.requestReturnsOK;


public class RequestSpecs {
    private static final Map<String, RequestSpecification> SPEC_CACHE = new ConcurrentHashMap<>();

    private static RequestSpecBuilder defaultRequestBuilder() {
        return new RequestSpecBuilder().setContentType(ContentType.JSON).setAccept(ContentType.JSON).addFilters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter())).setBaseUri("http://localhost:4111");
    }
    private RequestSpecs() {}

    public static RequestSpecification unauthSpec() {
        return defaultRequestBuilder().build();
    }

    public static RequestSpecification adminSpec() {
        return defaultRequestBuilder().addHeader("Authorization", "Basic YWRtaW46YWRtaW4=").build();
    }

    public static RequestSpecification authAsUser(String username, String password) {
        String key = username + ":" + password;
        return SPEC_CACHE.computeIfAbsent(key, k -> {
            String token = new LoginUserRequester(unauthSpec(), requestReturnsOK())
                    .post(LoginUserRequest.builder()
                            .username(username)
                            .password(password)
                            .build())
                     .extract()
                     .header("Authorization");

            if (token == null || token.isEmpty()) {
                throw new IllegalStateException("Login failed for " + username);
            }

            return defaultRequestBuilder().addHeader("Authorization", token).build();
        });
    }

}