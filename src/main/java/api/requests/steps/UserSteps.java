package api.requests.steps;

import api.models.*;
import api.requests.skeleton.Endpoint;
import api.requests.skeleton.requester.CrudRequester;
import api.requests.skeleton.requester.ValidatedCrudRequester;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import io.restassured.specification.ResponseSpecification;

import java.util.List;

public class UserSteps {
    private String username;
    private String password;

    public UserSteps(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<CreateAccountResponse> getAllAccounts() {
        return new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.USER_GET_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()).getAll(CreateAccountResponse[].class);
    }

    public List<CreateAccountResponse> getAllAccounts(String username, String password) {
        return new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.USER_GET_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()).getAll(CreateAccountResponse[].class);
    }


    public static AccountResponse createAccount(String username, String password) {
        return new ValidatedCrudRequester<AccountResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()).postAndExtract(null);
    }

    public static AccountResponse makeDeposit(String username, String password,
                                              long accountId, double amount) {
        DepositRequest request = DepositRequest.builder()
                .accountId(accountId)
                .amount(amount)
                .build();

        return new ValidatedCrudRequester<AccountResponse>(
                RequestSpecs.authAsUser(username, password),
                Endpoint.ACCOUNTS_DEPOSIT,
                ResponseSpecs.depositAccepted()
        ).postAndExtract(request);
    }

    public static String makeDepositFails(String username, String password,
                                          long accountId, double amount) {
        DepositRequest request = DepositRequest.builder()
                .accountId(accountId)
                .amount(amount)
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
                .accountId(accountId)
                .amount(amount)
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

    public long getFirstAccountID() {
        return getAllAccounts().get(0).getId();
    }

    public double getAccountBalance() {
        return getAllAccounts().get(0).getBalance();
    }

    public String getAccountNumber() {
        return getAllAccounts(username, password).get(0).getAccountNumber();
    }

     public void makeDeposit20000() {
       long accountId = getFirstAccountID();
       double amount = 5000;
        for (int i = 0; i < 4; i++) {
            makeDeposit(username, password,
                    accountId, amount);
        }
    }

    public CreateAccountResponse getFirstAccount(CreateUserRequest user) {
        return getAllAccounts(user.getUsername(), user.getPassword()).get(0);
    }
}
