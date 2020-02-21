package kz.dits.billing.service;

import kz.dits.billing.command.CreateAccountCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class AccountCommandService {

    private CommandGateway commandGateway;

    public CompletableFuture createAccount(CreateAccountCommand command) {

        return commandGateway.send(command);
    }
}
