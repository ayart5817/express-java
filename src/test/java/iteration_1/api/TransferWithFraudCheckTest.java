package iteration_1.api;

import api.models.*;
import api.models.comparison.ModelAssertions;
import api.requests.steps.AccountSteps;
import api.requests.steps.AdminSteps;
import common.annotations.FraudCheckMock;
import iteration_1.common.extensions.TimingExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ExtendWith(TimingExtension.class)
public class TransferWithFraudCheckTest extends BaseTest {

    @RegisterExtension
    static FraudCheckWireMockExtension fraudCheckWireMock = new FraudCheckWireMockExtension();

    private CreateUserRequest user1;
    private CreateUserRequest user2;
    private CreateAccountResponse account1;
    private CreateAccountResponse account2;
    private DepositResponse depositResponse;
    private TransferResponse transferResponse;
    private String baseUrl;

    @BeforeEach
    public void setupTest() {
        this.softly = new SoftAssertions();
        this.baseUrl = fraudCheckWireMock.getBaseUrl();
        System.out.println("=== Test using WireMock base URL: " + baseUrl + " ===");
    }

    @Test
    @FraudCheckMock(
            status = "SUCCESS",
            decision = "APPROVED",
            riskScore = 0.2,
            reason = "Low risk transaction",
            requiresManualReview = false,
            additionalVerificationRequired = false
    )
    public void testTransferWithFraudCheck() {
        // 1. Создаем пользователя через прямой REST запрос
        String adminUrl = baseUrl + "/api/v1/admin/users";
        System.out.println("Creating user at: " + adminUrl);

        user1 = CreateUserRequest.builder()
                .username("testuser1")
                .password("Password123!")
                .role("USER")
                .build();

        // Отправляем прямой запрос на создание пользователя
        CreateUserResponse userResponse1 = given()
                .contentType("application/json")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body(user1)
                .when()
                .post(adminUrl)
                .then()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);

        System.out.println("User created with ID: " + userResponse1.getId());

        // 2. Создаем счет для первого пользователя
        AccountSteps accountSteps1 = new AccountSteps(
                user1.getUsername(),
                user1.getPassword(),
                baseUrl
        );
        account1 = accountSteps1.createAccount();
        System.out.println("Account created with ID: " + account1.getId());

        // 3. Делаем депозит
        double depositAmount = Math.random() * 4999.9 + 0.1;
        depositResponse = accountSteps1.depositToAccount(account1.getId(), depositAmount);
        System.out.println("Deposit made: " + depositAmount);

        // 4. Создаем второго пользователя
        user2 = CreateUserRequest.builder()
                .username("testuser2")
                .password("Password456!")
                .role("USER")
                .build();

        CreateUserResponse userResponse2 = given()
                .contentType("application/json")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body(user2)
                .when()
                .post(adminUrl)
                .then()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);

        System.out.println("Second user created with ID: " + userResponse2.getId());

        // 5. Создаем счет для второго пользователя
        AccountSteps accountSteps2 = new AccountSteps(
                user2.getUsername(),
                user2.getPassword(),
                baseUrl
        );
        account2 = accountSteps2.createAccount();

        // 6. Делаем перевод с проверкой на фрод
        double transferAmount = Math.random() * (depositAmount - 0.1) + 0.1;

        TransferRequest transferRequest = TransferRequest.builder()
                .fromAccountId(account1.getId())
                .toAccountId(account2.getId())
                .amount(transferAmount)
                .build();

        String transferUrl = baseUrl + "/api/v1/accounts/transfer/fraud-check";
        System.out.println("Making transfer at: " + transferUrl);

        transferResponse = given()
                .contentType("application/json")
                .header("Authorization", accountSteps1.getAuthHeader())
                .body(transferRequest)
                .when()
                .post(transferUrl)
                .then()
                .statusCode(200)
                .extract()
                .as(TransferResponse.class);

        softly.assertThat(transferResponse).isNotNull();
        System.out.println("Transfer response: " + transferResponse);

        // 7. Проверяем ответ
        TransferResponse expectedResponse = TransferResponse.builder()
                .status("APPROVED")
                .message("Transfer approved and processed immediately")
                .amount(transferAmount)
                .senderAccountId(account1.getId())
                .receiverAccountId(account2.getId())
                .fraudRiskScore(0.2)
                .fraudReason("Low risk transaction")
                .requiresManualReview(false)
                .requiresVerification(false)
                .build();

        ModelAssertions.assertThatModels(expectedResponse, transferResponse).match();
    }

    @AfterEach
    public void afterTest() {
        softly.assertAll();
    }
}