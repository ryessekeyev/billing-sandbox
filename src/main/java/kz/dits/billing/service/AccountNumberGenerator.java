package kz.dits.billing.service;

import kz.dits.billing.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class AccountNumberGenerator {

    private final String PREFIX = "18";
    private final AccountRepository accountRepository;

    public String generate() {
        String accountNumber = generateCandidate();
        while (accountRepository.findOneByAccountNumber(accountNumber) != null) {
            accountNumber = generate();
        }
        return accountNumber;
    }

    private String generateCandidate() {
        LocalDate localDate = LocalDate.now();
        return localDate.format(DateTimeFormatter.BASIC_ISO_DATE) + PREFIX + RandomStringUtils.randomNumeric(5);
    }
}
