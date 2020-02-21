package kz.dits.billing.service;

import kz.dits.billing.command.CreatePaymentCommand;
import kz.dits.billing.command.MakeDepositCommand;
import kz.dits.billing.command.MakePaymentCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by rustem on 3/10/2017.
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PaymentCommandService {

    private CommandGateway commandGateway;

    @Async
    public void process(CreatePaymentCommand command) {
        log.info("Received CreatePaymentCommand {}", command);

        commandGateway.send(command, new CommandCallback<CreatePaymentCommand, UUID>() {
            @Override
            public void onSuccess(CommandMessage<? extends CreatePaymentCommand> commandMessage, UUID result) {
                log.info("SUCCESS");
                log.info("Success commandMessage: {}", commandMessage);
                log.info("Success Result: {}", result);

                MakeDepositCommand depositCommand = new MakeDepositCommand(
                    command.getAccountNumber(),
                    command.getCash().subtract(command.getChange())
                );

                log.info("Sending MakeDepositCommand: {}", depositCommand);
                commandGateway.send(depositCommand);

                MakePaymentCommand paymentCommand = new MakePaymentCommand(
                    command.getAccountNumber(),
                    command.getCash().subtract(command.getChange())
                );

                log.info("Sending MakePaymentCommand: {}", paymentCommand);
                commandGateway.send(paymentCommand);
            }

            @Override
            public void onFailure(CommandMessage<? extends CreatePaymentCommand> commandMessage, Throwable cause) {
                log.info("FAILURE");
                log.info("Failure commandMessage: {}", commandMessage);
                log.info("Failure cause: {}", cause.getLocalizedMessage());
            }
        });
    }
}
