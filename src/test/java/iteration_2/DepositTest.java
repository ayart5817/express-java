package iteration_2;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepositTest {
    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.filters(
                List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));

    }

    String userAuthHeader1 = "Basic QXlyYXQ6QXlyYXQxMjM0QA==";
    String userAuthHeader2 = "Basic QXlyYXQyOkF5cmF0MTIzNDVA";

    @Order(1)
    @Test
    public void user1GenerateTest() {
        //создание пользователя
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                        "username": "Ayrat",
                        "password": "Ayrat1234@",
                        "role": "USER"
                        }
                        """)
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat();
    }

    @Order(2)
    @Test
    public void user1GetTokenTest() {
        userAuthHeader1 = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                        "username": "Ayrat",
                        "password": "Ayrat1234@"
                        }
                        """)
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .extract()
                .header("Authorization");

        //создаем аккаунт пользователю u1
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("accountNumber", Matchers.equalTo("ACC1"))
                .body("balance", Matchers.equalTo(0.0F))
                .body("transactions", Matchers.empty())  ;

    }

    @Order(3)
    @Test
    public void user2GenerateTest() {
        //создание пользователя 2
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                        "username": "Ayrat2",
                        "password": "Ayrat12345@",
                        "role": "USER"
                        }
                        """)
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Order(4)
    @Test
    //ТОКЕН ЮЗЕРА 2
    public void user2CanGenerateAuthTokenTest() {
        //получаем токен
        userAuthHeader2 = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "Ayrat2",
                          "password": "Ayrat12345@"
                        }
                        """)
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .header("Authorization");
        //создаем аккаунт юзер 2
        given().header("Authorization", userAuthHeader2)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

    }

    @Order(5)
    @Test
    //Проверка значения транзакции по умолчанию
    public void checkDefaultValueTransactionU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .get("http://localhost:4111/api/v1/accounts/2/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$", empty());
    }

    @Order(6)
    @Test
    //проверяем депозит на 4999.99 голд паф U2
    public void deposit4999FoU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                         "id": 2,
                         "balance": 4999.99
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Order(7)
    @Test
    //Проверка состояния счета после транзакции 4999,99 U2

    public void ValueTransactionAfterDeposit4999U2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .get("http://localhost:4111/api/v1/accounts/2/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasSize(1))
                .body("[0].amount", equalTo(4999.99f))
                .body("[0].type", equalTo("DEPOSIT"))
                .body("[0].relatedAccountId", equalTo(2));
    }

    @Order(8)
    @Test
    //проверяем депозит границу 5000 голд U2
    public void deposit50000FoU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                         "id": 2,
                         "balance": 5000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("balance", equalTo(9999.99F))
                .body("transactions.size()", equalTo(2))
                .body("transactions[0].amount", equalTo(5000.0F))
                .body("transactions[0].type", equalTo("DEPOSIT"))
                .body("transactions[0].relatedAccountId", equalTo(2));
    }

    @Order(9)
    @Test
    //проверяем депозит 5000.01 минимальная негативная граница
    public void negativeDeposit1000FoU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                         "id": 2,
                         "balance": 5000.01
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Deposit amount cannot exceed 5000"));
    }

    @Order(10)
    @Test
    //Проверка состояния счета после транзакции 5000.00 и 5000.01 U2
    // ожидаем 9999.99

    public void ValueTransactionAfterDeposit5000and5001U2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .get("http://localhost:4111/api/v1/accounts/2/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasSize(2))
                .body("[0].amount", equalTo(5000.0F))
                .body("[0].type", equalTo("DEPOSIT"))
                .body("[0].relatedAccountId", equalTo(2));
    }

    @Order(11)
    @Test
    //Проверим состояние счета не изменилось после негативного кейса с сумой 5000.01
    public void getCustomerAccountsU2TEst() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                // Проверяем, что вернулись ровно 2 счёта
                .body("$", hasSize(1))
                // Проверка счёта ACC2
                .body("[0].id", equalTo(2))
                .body("[0].accountNumber", equalTo("ACC2"))
                .body("[0].balance", equalTo(9999.99f))
                .body("[0].transactions", hasSize(2));


    }

    @Order(12)
    @Test
    //проверяем депозит мин границу 0.01 U1
    public void depositMin01BoundaryValueTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                         "id": 1,
                         "balance": 0.01
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Order(13)
    @Test
    //проверяем депозит на 0.00
    public void depositMin0BoundaryValueTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                         "id": 1,
                         "balance": 0.00
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Deposit amount must be at least 0.01"));

    }

    @Order(14)
    @Test
    //проверяем депозит на -0.01
    public void depositMinNegativeBoundaryValueTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                         "id": 1,
                         "balance": -0.01
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Deposit amount must be at least 0.01"));

    }

    @Order(15)
    @Test
    //проверяем депозит на 1000 чужой аккаунт
    public void depositNegativeAlenAccountTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                         "id": 2,
                         "balance": 1000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(org.hamcrest.Matchers.containsString("Unauthorized access to account"));

    }

    @Order(16)
    @Test
    //Проверим состояние счета после кейсов для U1
    // expect 0.01
    public void getCustomerAccountsAfterTestsU1TEst() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                // Проверяем, что вернулись ровно 2 счёта
                .body("$", hasSize(1))
                // Проверка счёта ACC2
                .body("[0].id", equalTo(1))
                .body("[0].accountNumber", equalTo("ACC1"))
                .body("[0].balance", equalTo(0.01f))
                .body("[0].transactions", hasSize(1));


    }

    @Order(17)
    @Test
    //проверяем трансфер Сумма больше счета u1-> u2 минимальная гораница
    public void TransferTransferMoreBalanceMinBoundaryU1Test() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                          "senderAccountId": 1,
                          "receiverAccountId": 2,
                          "amount": 0.02
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Invalid transfer: insufficient funds or invalid accounts"));


    }

    @Order(18)
    @Test
    //проверяем трансфер Сумма больше счета u1-> u2 максимальная граница 9999.99 депозит трансфер 10к
    public void TransferTransferMoreBalanceMaxBoundaryU1Test() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                          "senderAccountId": 1,
                          "receiverAccountId": 2,
                          "amount": 1
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Invalid transfer: insufficient funds or invalid accounts"));


    }

    @Order(19)
    @Test
    //Подготовка депозита 10к
    public void deposit5000FoU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                         "id": 2,
                         "balance": 5000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }

    @Order(20)
    @Test
    //Подготовка депозита 15к
    public void deposit10000FoU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                         "id": 2,
                         "balance": 5000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }

    @Order(21)
    @Test
    //Подготовка депозита 20к
    public void deposit15000FoU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                         "id": 2,
                         "balance": 5000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("balance", equalTo(24999.99F))
                .body("transactions.size()", equalTo(5))
                .body("transactions[0].amount", equalTo(5000.0f))
                .body("transactions[0].type", equalTo("DEPOSIT"))
                .body("transactions[0].relatedAccountId", equalTo(1));
    }

    @Order(22)
    @Test
    //проверяем трансфер на 10000.01 u2-> u1
    public void TransferNegativeMaxBoundaryTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                          "senderAccountId": 2,
                          "receiverAccountId": 1,
                          "amount": 10000.01
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Transfer amount cannot exceed 10000"));
    }

    @Order(23)
    @Test
    //проверяем трансфер на 10000.00 u2-> u1
    public void TransferMaxBoundaryTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                          "senderAccountId": 2,
                          "receiverAccountId": 1,
                          "amount": 10000.00
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("amount", equalTo(10000.0f))
                .body("receiverAccountId", equalTo(1f))
                .body("senderAccountId", equalTo( 2f))
                .body("message", equalTo("Transfer successful"));


    }

    @Order(24)
    @Test
    //Проверим состояние счета после кейсов U2-U1
    // expect 10000.01
    public void getCustomerAccountsAfterTransferU1TEst() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                // Проверяем, что вернулись ровно 3 счёта
                .body("$", hasSize(1))
                // Проверка счёта ACC2
                .body("[0].id", equalTo(1))
                .body("[0].accountNumber", equalTo("ACC1"))
                .body("[0].balance", equalTo(10000.01f))
                .body("[0].transactions", hasSize(2));
    }

    @Order(25)
    @Test
    //проверяем трансфер на -0.01 u1-> u2
    public void TransferNegativeMinBoundaryTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                          "senderAccountId": 1,
                          "receiverAccountId": 2,
                          "amount": -0.01
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Transfer amount must be at least 0.01"));


    }

    @Order(26)
    @Test
    //проверяем трансфер перевода самому себе (должен быть запрет)
    public void TransferToHimselfTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                          "senderAccountId": 1,
                          "receiverAccountId": 1,
                          "amount": 1
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Invalid transfer: insufficient funds or invalid accounts"));

    }

    @Order(27)
    @Test
    //Проверим состояние счета после кейсов U2
    // expect 14999.99
    public void getCustomerAccountsAfterTransferU2TEst() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                // Проверяем, что вернулись ровно 3 счёта
                .body("$", hasSize(1))
                // Проверка счёта ACC2
                .body("[0].id", equalTo(2))
                .body("[0].accountNumber", equalTo("ACC2"))
                .body("[0].balance", equalTo(14999.99f))
                .body("[0].transactions", hasSize(6));


    }

    @Order(28)
    @Test
    //проверяем изменение профиля для юзера 2 15 символов
    public void changeProfilePositiveU2Test() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                          "name": "Qwertyu Iopasdf"
                        }
                        """)
                .when()
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("message", containsString("Profile updated successfully"))
                .body("customer.id", equalTo(2))
                .body("customer.username", equalTo("Ayrat2"))
                .body("customer.name", equalTo("Qwertyu Iopasdf"))
                .body("customer.accounts.size()", greaterThan(0));

    }

    @Order(29)
    @Test
    public void getaProfileAfterUpdateNameTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .when()
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(2))
                .body("username", equalTo("Ayrat2"))
                .body("name", equalTo("Qwertyu Iopasdf"))
                .body("role", equalTo("USER"))
                .body("password", not(emptyOrNullString()));
    }

    @Order(30)
    @Test
    //проверяем изменение профиля для юзера имя 2 символа
    public void changeProfileThueSimbolU2Test() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                          "name": "m r"
                        }
                        """)
                .when()
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);


    }

    @Order(31)
    @Test
    public void getaProfileAfterUpdateNameTwoSimbolTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .when()
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(2))
                .body("username", equalTo("Ayrat2"))
                .body("name", equalTo("m r"))
                .body("role", equalTo("USER"))
                .body("password", not(emptyOrNullString()));
    }


    @Order(32)
    @Test
    //проверяем изменение профиля для юзера 2 пусто
    public void negativeChangeProfileU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                          "name": ""
                        }
                        """)
                .when()
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Name must contain two words with letters only"));


    }

    @Order(33)
    @Test
    //проверяем что имя не изменилось поле ошибки
    public void getProfileAfterNotUpdateNameTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .when()
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(2))
                .body("username", equalTo("Ayrat2"))
                .body("name", equalTo("m r"))
                .body("role", equalTo("USER"))
                .body("password", not(emptyOrNullString()));
    }

    @Order(34)
    @Test
    //проверяем изменение профиля для юзера 2 цифры в имени
    public void negativeChangeProfileHaveNumbersU2() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                          "name": "a 2"
                        }
                        """)
                .when()
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(org.hamcrest.Matchers.containsString("Name must contain two words with letters only"));


    }

    @Order(35)
    @Test
    //проверяем изменение профиля для юзера максимально длинное имена
    public void maxLengNegativeUserNameU2Test() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                          "name": "aaaaaaaaaaaaaa bbbbbbddddddddddddddddddddddddddddddddbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbjjjjjjjjjjjjjjjjjjjjjjjjjjj"
                        }
                        """)
                .when()
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", org.hamcrest.Matchers.containsString("Name must contain two words with letters only"));


    }

    @Order(36)
    @Test
    public void getaProfileAfterNotUpdateNameAfterNegativeTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .when()
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(2))
                .body("username", equalTo("Ayrat2"))
                .body("name", equalTo("m r"))
                .body("role", equalTo("USER"))
                .body("password", not(emptyOrNullString()));
    }


}