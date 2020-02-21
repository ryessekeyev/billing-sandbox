package kz.dits.billing.event;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by rustem on 3/6/2017.
 */
@Value
public class AccountCreatedEvent {

    @NonNull private String accountNumber;
    @NonNull private BigDecimal balance;
    private Instant createdDate;
}
