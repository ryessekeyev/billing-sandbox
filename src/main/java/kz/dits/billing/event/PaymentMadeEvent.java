package kz.dits.billing.event;

import lombok.Value;

import java.math.BigDecimal;

/**
 * Created by rustem on 3/13/2017.
 */
@Value
public class PaymentMadeEvent {

    private String accountNumber;
    private BigDecimal amount;
    private BigDecimal balance;
}
