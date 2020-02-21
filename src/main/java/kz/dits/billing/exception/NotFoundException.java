package kz.dits.billing.exception;

/**
 * Created by rustem on 4/19/2017.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
