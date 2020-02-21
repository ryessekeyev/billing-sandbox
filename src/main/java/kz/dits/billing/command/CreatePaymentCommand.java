package kz.dits.billing.command;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by rustem on 3/2/2017.
 */
@Builder
@Value
public class CreatePaymentCommand implements Serializable {

    @NonNull
    private UUID uuid;
    @TargetAggregateIdentifier
    private String accountNumber;
    @NonNull
    private BigDecimal cash;
    @NonNull
    private BigDecimal change;
}
