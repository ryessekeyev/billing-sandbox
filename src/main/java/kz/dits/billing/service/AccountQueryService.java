package kz.dits.billing.service;

import kz.dits.billing.domain.Account;
import kz.dits.billing.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by ryessekeyev on 11/11/2016.
 */
@Slf4j
@Service("default")
@Primary
@AllArgsConstructor
public class AccountQueryService {

    private AccountRepository accountRepository;

    public Account getAccountByAccountNumber(String accountNumber) {
        log.info("getAccountByAccountNumber: {}", accountNumber);
        return accountRepository.findOneByAccountNumber(accountNumber);
    }

}
