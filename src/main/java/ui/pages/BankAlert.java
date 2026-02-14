package ui.pages;

public enum BankAlert {
    USER_CREATED_SUCCESSFULLY("✅ User created successfully!"),
    USERNAME_MUST_BE_3_AND_15_CHARACTERS("Username must be between 3 and 15 characters"),
    NEW_ACCOUNT_CREATED("✅ New Account Created! Account Number: ");

    private final String message;
    BankAlert(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
