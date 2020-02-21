package kz.dits.billing.event;

import lombok.NonNull;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

/**
 * Created by rustem on 3/6/2017.
 */
@Value
public class DepositMadeEvent {

    @NonNull @TargetAggregateIdentifier private String accountNumber;
    private BigDecimal amount;
    @NonNull private BigDecimal balance;

}
