package kz.dits.billing.event.handler;

import kz.dits.billing.domain.Account;
import kz.dits.billing.event.AccountCreatedEvent;
import kz.dits.billing.event.DepositMadeEvent;
import kz.dits.billing.event.PaymentMadeEvent;
import kz.dits.billing.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

/**
 * Created by rustem on 3/6/2017.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountEventHandler {

    private AccountRepository accountRepository;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        log.info("AccountEventHandler handling AccountCreatedEvent");
        accountRepository.save(new Account(
            event.getAccountNumber(),
            event.getBalance(),
            event.getCreatedDate()));
    }

    @EventHandler
    public void on(DepositMadeEvent event) {
        log.info("AccountEventHandler handling DepositMadeEvent: {}", event);
        Account account = accountRepository.findOneByAccountNumber(event.getAccountNumber());
        accountRepository.setBalanceFor(event.getBalance(), account.getId());
    }

    @EventHandler
    public void on(PaymentMadeEvent event) {
        log.info("AccountEventHandler handling PaymentMadeEvent: [{}]", event);
        Account account = accountRepository.findOneByAccountNumber(event.getAccountNumber());
        accountRepository.setBalanceFor(event.getBalance(), account.getId());
    }
}
