package kz.dits.billing.service;

import kz.dits.billing.command.CreatePaymentCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rustem on 3/10/2017.
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PaymentCommandService {

    private CommandGateway commandGateway;

    public void process(CreatePaymentCommand command) {
        log.info("Received CreatePaymentCommand {}", command);

        commandGateway.send(command);
    }
}
