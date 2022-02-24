package exception;

public class AccountOperationsException extends RuntimeException {

    public static final String ACCOUNT_NOT_FOUND = "Account not found.";

    public AccountOperationsException(String message) {
        super(message);
    }
}
