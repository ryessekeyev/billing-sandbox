package kz.dits.billing.exception;

/**
 * Created by rustem on 4/19/2017.
 */
public class AccountNotFoundException extends NotFoundException {

    public AccountNotFoundException(String rId, String message) {
        super("rId: " + rId + ", " + message);
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(Long id) {
        super("Account for id: [" + id + "] was not found");
    }
}
