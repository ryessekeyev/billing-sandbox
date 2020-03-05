package kz.dits.billing.listener;

import kz.dits.billing.command.CreatePaymentCommand;
import kz.dits.billing.service.PaymentCommandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor @Slf4j
public class PaymentEventListener {

    private PaymentCommandService paymentCommandService;

    @RabbitListener(queues = "${application.rabbitmq.paymentEventQueue}")
    public void processMessage(CreatePaymentCommand command) {
        log.info("rid: {}, Received Payment: {}", command);
        throw new RuntimeException("Test Exception");
        //paymentCommandService.process(command);
    }
}
