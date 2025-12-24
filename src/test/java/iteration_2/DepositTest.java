package iteration_2;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class DepositTest {
    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.filters(
                List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));

    }

    String userAuthHeader1 = "QXlyYXQxMjM0OkF5cmF0MTIzNDVA";
    String userAuthHeader2 = "QXlyYXQxOkF5cmF0MTIzNDVA";

    @Test
    public void user1GenerateTest() {
        //создание пользователя
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                        "username": "Ayrat1",
                        "password": "Ayrat12345@",
                        "role": "USER"
                        }
                        """)
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat();
    }

    @Test
    public void user1GetTokenTest() {
        userAuthHeader1 = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                        "username": "Ayrat2",
                        "password": "Ayrat123@"
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
                .body("balance", Matchers.equalTo(0.0))
                .body("transactions", Matchers.equalTo("user"));

    }

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

    @Test
    //ТОКЕН ЮЗЕРА 2
    public void user2CanGenerateAuthTokenTest() {
        //получаем токен
        userAuthHeader2 = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "Ayrat1",
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

    @Test
    //Проверка значения транзакции по умолчанию

    public void checkDefaultValueTransactionU1() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .get("http://localhost:4111/api/v1/accounts/1/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("accountNumber", not(emptyOrNullString()))
                .body("balance", equalTo(0.0f))
                .body("transactions", empty());
    }

    @Test
    //проверяем депозит 4999.99 голд паф
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

    @Test
    //проверяем депозит границу 5000 голд паф
    public void deposit50000FoU1() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                         "id": 1,
                         "balance": 5000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("balance", equalTo(5000.0F))
                .body("transactions.size()", equalTo(1))
                .body("transactions[0].amount", equalTo(5000.0f))
                .body("transactions[0].type", equalTo("DEPOSIT"))
                .body("transactions[0].relatedAccountId", equalTo(1));
    }

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
                .body("message", org.hamcrest.Matchers.containsString("Deposit amount cannot exceed 5000"));
    }

    @Test
    //проверяем депозит мин границу 0.01
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
                .body("message", org.hamcrest.Matchers.containsString("Deposit amount must be at least 0.01"));

    }

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
                .body("message", org.hamcrest.Matchers.containsString("Deposit amount must be at least 0.01"));

    }

    @Test
    //проверяем депозит на -0.01
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
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", org.hamcrest.Matchers.containsString("Unauthorized access to account"));

    }


    @Test
    //Подготовка депозита 10к
    public void deposit50000FoU11() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                         "id": 1,
                         "balance": 5000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    //Подготовка депозита 15к
    public void deposit50000FoU111() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                         "id": 1,
                         "balance": 5000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }


    @Test
    //Подготовка депозита 20к
    public void deposit50000FoU1111() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                         "id": 1,
                         "balance": 5000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("balance", equalTo(5000.0F))
                .body("transactions.size()", equalTo(1))
                .body("transactions[0].amount", equalTo(5000.0f))
                .body("transactions[0].type", equalTo("DEPOSIT"))
                .body("transactions[0].relatedAccountId", equalTo(1));
    }

    @Test
    //проверяем трансфер на 10000.01 u1-> u2
    public void TransferNegativeMaxBoundaryTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                          "senderAccountId": 1,
                          "receiverAccountId": 2,
                          "amount": 10000.01
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", org.hamcrest.Matchers.containsString("Transfer amount cannot exceed 10000"));
    }

    @Test
    //проверяем трансфер на 10000.00 u1-> u2
    public void TransferMaxBoundaryTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                          "senderAccountId": 1,
                          "receiverAccountId": 2,
                          "amount": 10000.00
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("message", org.hamcrest.Matchers.containsString("Transfer successful"))
                .body("amount", equalTo(1000.00))
                .body("senderAccountId", equalTo(1))
                .body("receiverAccountId", equalTo(2));


    }

    @Test
    //проверяем трансфер на 0.01 u1-> u2
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
                .body("message", org.hamcrest.Matchers.containsString("Transfer amount must be at least 0.01"));


    }

    @Test
    //проверяем трансфер Сумма больше счета
    public void TransferZeroBoundaryTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader1)
                .body("""
                        {
                          "senderAccountId": 1,
                          "receiverAccountId": 2,
                          "amount": 10000
                        }
                        """)
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", org.hamcrest.Matchers.containsString("Invalid transfer: insufficient funds or invalid accounts"));


    }


    @Test
    //проверяем трансфер перевода самому себе (должен быть запрет?)
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
                .statusCode(HttpStatus.SC_OK)
                .body("message", org.hamcrest.Matchers.containsString("Transfer successful"))
                .body("amount", equalTo(1.0))
                .body("senderAccountId", equalTo(1))
                .body("receiverAccountId", equalTo(1));

    }

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
                .body("message", org.hamcrest.Matchers.containsString("Profile updated successfully"))
                .body("customer.id", equalTo(1))
                .body("username", equalTo("Ayrat1"))
                .body("customer.accounts.size()", org.hamcrest.Matchers.greaterThan(0))
                .body("name", equalTo("йцукенгшщзхъфыв"));

    }

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
                .body("message", org.hamcrest.Matchers.containsString("Name must contain two words with letters only"));


    }

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
                .body("message", org.hamcrest.Matchers.containsString("Name must contain two words with letters only"));


    }

    @Test
    //проверяем изменение профиля для юзера максимально длинное имена
    public void maxLengNegativeUserNameU2Test() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthHeader2)
                .body("""
                        {
                          "name": "aaaaaaaaaaaaaa bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
                        }
                        """)
                .when()
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", org.hamcrest.Matchers.containsString("Name must contain two words with letters only"));


    }


}