package kz.dits.billing.domain;

import kz.dits.billing.command.CreatePaymentCommand;
import kz.dits.billing.event.PaymentCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Created by rustem on 18.07.2017.
 */
@Slf4j
@Aggregate
@NoArgsConstructor
public class PaymentAggregate {

    @AggregateIdentifier
    private UUID uuid;
    private boolean reversed;

    @CommandHandler
    public PaymentAggregate(CreatePaymentCommand command) {
        log.info("Handling CreatePaymentCommand");
        PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.mapToPaymentCreatedEvent(command);
        apply(paymentCreatedEvent);
    }

    @EventSourcingHandler
    public void on(PaymentCreatedEvent event) {
        this.uuid = event.getUuid();
        this.reversed = false;
    }
}
