package kz.dits.billing.web.rest;

import kz.dits.billing.command.CreateAccountCommand;
import kz.dits.billing.service.AccountCommandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

/**
 * Created by rustem on 3/6/2017.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountCommandResource {

    private AccountCommandService accountCommandService;

    @PostMapping("/accounts")
    public CompletableFuture createAccount(@RequestBody @Valid CreateAccountCommand command) {
        log.info("Received request: {}", command);
        return accountCommandService.createAccount(command);
    }
}
