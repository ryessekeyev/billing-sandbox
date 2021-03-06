package kz.dits.billing.command;

import lombok.NonNull;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

/**
 * Created by rustem on 3/6/2017.
 */
@Value
public class MakeDepositCommand {

    @NonNull
    @TargetAggregateIdentifier
    private String accountNumber;
    @NonNull
    private BigDecimal amount;

}
