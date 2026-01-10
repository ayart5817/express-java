package requests.steps;

import io.restassured.specification.ResponseSpecification;
import models.*;
import requests.skeleton.Endpoint;
import requests.skeleton.requester.CrudRequester;
import requests.skeleton.requester.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.List;

public class UserSteps {

    public static AccountResponse createAccount(String username, String password) {
        return new ValidatedCrudRequester<AccountResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).postAndExtract(null);
    }

    public static AccountResponse makeDeposit(String username, String password, long accountId, double amount) {
        DepositRequest request = DepositRequest.builder()
                .id(accountId)
                .balance(amount)
                .build();

        return new ValidatedCrudRequester<AccountResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS_DEPOSIT,
                ResponseSpecs.depositAccepted()
        ).postAndExtract(request);
    }

    public static String makeDepositFails(String username, String password, long accountId, double amount) {
        DepositRequest request = DepositRequest.builder()
                .id(accountId)
                .balance(amount)
                .build();

        return new CrudRequester(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS_DEPOSIT,
                ResponseSpecs.depositRejectedPlainText()
        ).post(request)
                .extract()
                .asString();
    }

    public static String makeDepositForbidden(String username, String password, long accountId, double amount) {
        DepositRequest request = DepositRequest.builder()
                .id(accountId)
                .balance(amount)
                .build();

        return new CrudRequester(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS_DEPOSIT,
                ResponseSpecs.forbiddenAccess()
        ).post(request)
                .extract()
                .asString();
    }

    public static List<Transaction> getTransactions(String username, String password, long accountId) {
        return new CrudRequester(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS_TRANSACTION,
                ResponseSpecs.transactionsAccounts()
        ).get(accountId)
                .extract()
                .body()
                .jsonPath()
                .getList(".", Transaction.class); // ← точка "." означает корневой массив
    }

    // Успешный перевод
    public static TransferResponse makeTransfer(
            String username, String password,
            long senderAccountId, long receiverAccountId, double amount) {

        TransferRequest request = TransferRequest.builder()
                .senderAccountId(senderAccountId)
                .receiverAccountId(receiverAccountId)
                .amount(amount)
                .build();

        return new ValidatedCrudRequester<TransferResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS_TRANSFER,
                ResponseSpecs.transferAccepted()
        ).postAndExtract(request);
    }

    // Негативный перевод
    public static String makeTransferFails(
            String username, String password,
            long senderAccountId, long receiverAccountId, double amount,
            ResponseSpecification spec) {

        TransferRequest request = TransferRequest.builder()
                .senderAccountId(senderAccountId)
                .receiverAccountId(receiverAccountId)
                .amount(amount)
                .build();

        return new CrudRequester(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS_TRANSFER,
                spec
        ).post(request)
                .extract()
                .asString();
    }
}
