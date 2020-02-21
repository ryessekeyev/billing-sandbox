package kz.dits.billing.exception;

/**
 * Created by rustem on 4/19/2017.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
