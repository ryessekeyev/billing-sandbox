package kz.dits.billing.event;

import kz.dits.billing.command.CreatePaymentCommand;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by rustem on 18.07.2017.
 */
@Value
@Builder
public class PaymentCreatedEvent {

    @NonNull
    private UUID uuid;
    private String accountNumber;
    @NonNull private BigDecimal cash;
    @NonNull private BigDecimal change;

    public static PaymentCreatedEvent mapToPaymentCreatedEvent(CreatePaymentCommand command) {
        if (command == null)
            return null;

        PaymentCreatedEvent event = PaymentCreatedEvent.builder()
            .uuid(command.getUuid())
            .accountNumber(command.getAccountNumber())
            .cash(command.getCash())
            .change(command.getChange())
            .build();

        return event;
    }
}
