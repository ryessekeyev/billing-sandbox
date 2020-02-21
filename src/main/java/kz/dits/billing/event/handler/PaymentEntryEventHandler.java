package kz.dits.billing.event.handler;

import kz.dits.billing.domain.Account;
import kz.dits.billing.domain.Entry;
import kz.dits.billing.event.PaymentMadeEvent;
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
@Component
@Slf4j
@AllArgsConstructor
public class PaymentEntryEventHandler {

    private AccountRepository accountRepository;
    private EntryRepository paymentEntryRepository;

    @EventHandler
    public void on(PaymentMadeEvent event, @Timestamp Instant instant) {
        Account account = accountRepository.findOneByAccountNumber(event.getAccountNumber());
        paymentEntryRepository.save(new Entry(
            account.getId(),
            event.getAccountNumber(),
            event.getAmount(),
            instant,
            "PAYMENT"));

    }
}
