package kz.dits.billing.web.rest.errors;

/**
 * Created by rustem on 20.06.2017.
 */
public class BillingRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;

    public BillingRuntimeException(String message) {
        super(message);
        this.message = message;
    }
}
