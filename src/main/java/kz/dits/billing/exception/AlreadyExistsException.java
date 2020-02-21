package kz.dits.billing.exception;

/**
 * Created by rustem on 19.07.2017.
 */
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
