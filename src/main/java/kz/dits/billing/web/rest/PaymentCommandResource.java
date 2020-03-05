package kz.dits.billing.web.rest;

import kz.dits.billing.command.CreatePaymentCommand;
import kz.dits.billing.service.PaymentCommandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by rustem on 3/2/2017.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PaymentCommandResource {
    private PaymentCommandService paymentCommandService;
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/payments")
    public ResponseEntity processPayment(@RequestBody @Valid CreatePaymentCommand command) {
        log.info("Received CreatePaymentRequest: {}", command);
        paymentCommandService.process(command);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/payments-test")
    public ResponseEntity processTwoPayment() {

        UUID paymentUuid_1 = UUID.randomUUID();
        CreatePaymentCommand command_1 = CreatePaymentCommand.builder()
            .uuid(paymentUuid_1)
            .accountNumber("123")
            .cash(BigDecimal.TEN)
            .change(BigDecimal.ZERO).build();

        UUID paymentUuid_2 = UUID.randomUUID();
        CreatePaymentCommand command_2 = CreatePaymentCommand.builder()
            .uuid(paymentUuid_2)
            .accountNumber("123")
            .cash(BigDecimal.TEN)
            .change(BigDecimal.ZERO).build();

        paymentCommandService.process(command_1);
        paymentCommandService.process(command_2);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/payments-rabbit")
    public ResponseEntity processTwoPaymentUsingRabbit() {

        UUID paymentUuid_1 = UUID.randomUUID();
        CreatePaymentCommand command_1 = CreatePaymentCommand.builder()
            .uuid(paymentUuid_1)
            .accountNumber("123")
            .cash(BigDecimal.TEN)
            .change(BigDecimal.ZERO).build();

        UUID paymentUuid_2 = UUID.randomUUID();
        CreatePaymentCommand command_2 = CreatePaymentCommand.builder()
            .uuid(paymentUuid_2)
            .accountNumber("123")
            .cash(BigDecimal.valueOf(50))
            .change(BigDecimal.ZERO).build();

        rabbitTemplate.convertAndSend("PAYMENTS", "PAYMENT", command_1);
        rabbitTemplate.convertAndSend("PAYMENTS", "PAYMENT", command_2);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
