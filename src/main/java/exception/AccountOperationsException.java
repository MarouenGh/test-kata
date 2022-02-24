package exception;

public class AccountOperationsException extends RuntimeException {
    public static final String DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT = "Deposit is less than minimum deposit";
    public static final String ACCOUNT_NOT_FOUND = "Account not found.";

    public AccountOperationsException(String message) {
        super(message);
    }
}
