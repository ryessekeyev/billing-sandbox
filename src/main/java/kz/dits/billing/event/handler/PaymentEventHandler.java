package kz.dits.billing.event.handler;

import kz.dits.billing.command.MakeDepositCommand;
import kz.dits.billing.command.MakePaymentCommand;
import kz.dits.billing.domain.Account;
import kz.dits.billing.domain.Payment;
import kz.dits.billing.event.PaymentCreatedEvent;
import kz.dits.billing.exception.AccountNotFoundException;
import kz.dits.billing.repository.AccountRepository;
import kz.dits.billing.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Created by rustem on 18.07.2017.
 */
@Slf4j
@Component
@AllArgsConstructor
public class PaymentEventHandler {

    private AccountRepository accountRepository;
    private PaymentRepository paymentRepository;
    private CommandGateway commandGateway;

    @EventHandler
    public void on(PaymentCreatedEvent event, @Timestamp Instant instant) {
        log.info("Handling PaymentCreatedEvent: {}", event);

        Account account = accountRepository.findOneByAccountNumber(event.getAccountNumber());
        if (account == null)
            throw new AccountNotFoundException("Account for account number: [" + event.getAccountNumber() + "] was not found");

        paymentRepository.save(new Payment(
            event.getUuid(),
            account.getId(),
            account.getAccountNumber(),
            instant,
            event.getCash(),
            event.getChange()
        ));

        MakeDepositCommand depositCommand = new MakeDepositCommand(
            event.getAccountNumber(),
            event.getCash().subtract(event.getChange())
        );

        log.info("Sending MakeDepositCommand: {}", depositCommand);
        commandGateway.send(depositCommand);

        MakePaymentCommand paymentCommand = new MakePaymentCommand(
            event.getAccountNumber(),
            event.getCash().subtract(event.getChange())
        );

        log.info("Sending MakePaymentCommand: {}", paymentCommand);
        commandGateway.send(paymentCommand);
    }

}
