package kz.dits.billing.domain;

import kz.dits.billing.command.CreateAccountCommand;
import kz.dits.billing.command.MakeDepositCommand;
import kz.dits.billing.command.MakePaymentCommand;
import kz.dits.billing.event.AccountCreatedEvent;
import kz.dits.billing.event.DepositMadeEvent;
import kz.dits.billing.event.PaymentMadeEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.Instant;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
public class AccountAggregate {

    public static final String LEGACY = "LEGACY";

    @AggregateIdentifier
    private String accountNumber;
    private BigDecimal balance;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        log.info("Handling CreateAccountCommand: {}", command);
        apply(new AccountCreatedEvent(
            command.getAccountNumber(),
            command.getBalance(),
            Instant.now()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountNumber = event.getAccountNumber();
        this.balance = event.getBalance();
    }

    @CommandHandler
    public void handleDeposit(MakeDepositCommand command) {
        log.info("Handling MakeDepositCommand: {}", command);
        Assert.notNull(command.getAccountNumber(), "account number must not be null");
        Assert.notNull(command.getAmount(), "amount must not be null");

        apply(new DepositMadeEvent(
            command.getAccountNumber(),
            command.getAmount(),
            balance.add(command.getAmount())));
    }

    @EventSourcingHandler
    public void on(DepositMadeEvent event) {
        this.balance = event.getBalance();
    }

    @CommandHandler
    public void handlePayment(MakePaymentCommand command) {
        log.info("Handling MakePaymentCommand: {}", command);
        Assert.notNull(command.getAccountNumber(), "account number must not be null");
        Assert.notNull(command.getAmount(), "amount must not be null");

        apply(new PaymentMadeEvent(
            command.getAccountNumber(),
            command.getAmount(),
            balance.subtract(command.getAmount())));
    }

    @EventSourcingHandler
    public void on(PaymentMadeEvent event) {
        this.balance = event.getBalance();
    }
}
