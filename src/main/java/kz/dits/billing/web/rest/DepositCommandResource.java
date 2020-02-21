package kz.dits.billing.web.rest;

import kz.dits.billing.command.MakeDepositCommand;
import kz.dits.billing.domain.Account;
import kz.dits.billing.exception.AccountNotFoundException;
import kz.dits.billing.service.AccountQueryService;
import kz.dits.billing.web.rest.request.MakeDepositRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * Rest controller for deposit operations
 *
 * Created by ryessekeyev on 12/21/2016.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DepositCommandResource {

    private AccountQueryService accountQueryService;
    private CommandGateway commandGateway;

    @PostMapping("/deposits")
    public CompletableFuture<ResponseEntity> deposit(@RequestBody MakeDepositRequest request) {
        log.info("Received: {}", request);

        String accountNumber = request.getAccountNumber();
        Account account = accountQueryService.getAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account for account number: [" + accountNumber + "] was not found");
        }

        return commandGateway.send(new MakeDepositCommand(
            account.getAccountNumber(),
            request.getAmount()));
    }
}
