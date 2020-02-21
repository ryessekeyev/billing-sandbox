package kz.dits.billing.event.handler;

import kz.dits.billing.domain.Account;
import kz.dits.billing.domain.Entry;
import kz.dits.billing.event.DepositMadeEvent;
import kz.dits.billing.repository.AccountRepository;
import kz.dits.billing.repository.EntryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Created by rustem on 4/6/2017.
 */
@Slf4j
@Component
@AllArgsConstructor
public class DepositEntryEventHandler {

    private AccountRepository accountRepository;
    private EntryRepository depositEntryRepository;

    @EventHandler
    public void on(DepositMadeEvent event, @Timestamp Instant instant) {
        log.info("DepositEntryEventHandler handling DepositMadeEvent: {}, at {}", event, instant);
        Account account = accountRepository.findOneByAccountNumber(event.getAccountNumber());
        depositEntryRepository.save(new Entry(
            account.getId(),
            event.getAccountNumber(),
            event.getAmount(),
            instant,
            "DEPOSIT"));
    }

}
