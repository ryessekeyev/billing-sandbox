package kz.dits.billing.web.rest.request;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

/**
 * Created by rustem on 3/6/2017.
 */
@Value
public class MakeDepositRequest {

    @NonNull private String accountNumber;
    @NonNull private BigDecimal amount;
}
