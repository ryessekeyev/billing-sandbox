package kz.dits.billing.web.rest.request;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by rustem on 19.07.2017.
 */
@Value
public class CreatePaymentRequest {

    @NotNull
    private UUID uuid;
    @NotNull
    private String accountNumber;
    @NotNull
    private BigDecimal cash;
    @NotNull
    private BigDecimal change;
}
