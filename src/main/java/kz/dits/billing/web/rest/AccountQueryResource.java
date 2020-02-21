package kz.dits.billing.web.rest;

import kz.dits.billing.domain.Account;
import kz.dits.billing.service.AccountQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ryessekeyev on 11/11/2016.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountQueryResource {

    private final AccountQueryService accountQueryService;

    @GetMapping("/accounts/search/byAccountNumber")
    public ResponseEntity<Account> getAccountByAccountNumber(@RequestParam(name = "accountNumber") String accountNumber) {
        log.info("get account by account number id: {}", accountNumber);
        Account account = accountQueryService.getAccountByAccountNumber(accountNumber);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
