package kz.dits.billing.service;

import kz.dits.billing.command.CreateAccountCommand;
import kz.dits.billing.command.CreatePaymentCommand;
import kz.dits.billing.domain.Payment;
import kz.dits.billing.repository.AccountRepository;
import kz.dits.billing.repository.EntryRepository;
import kz.dits.billing.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PaymentCommandServiceTest {

    @Autowired
    private PaymentCommandService paymentCommandService;
    @Autowired
    private AccountCommandService accountCommandService;
    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void testOnePaymentCommand() {
        createTestData();

        UUID paymentUuid_1 = UUID.randomUUID();
        CreatePaymentCommand command_1 = CreatePaymentCommand.builder()
            .uuid(paymentUuid_1)
            .accountNumber("123")
            .cash(BigDecimal.TEN)
            .change(BigDecimal.ZERO).build();

        paymentCommandService.process(command_1);

        Payment payment_1 = paymentRepository.findOne(paymentUuid_1);
        log.info("Payment_1: {}", payment_1);
        Assert.assertNotNull(payment_1);
    }

    @Test
    public void testTwoPaymentCommands() throws InterruptedException {
        createTestData();

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

        TimeUnit.SECONDS.sleep(5);

        Payment payment_1 = paymentRepository.findOne(paymentUuid_1);
        log.info("Payment_1: {}", payment_1);
        Assert.assertNotNull(payment_1);

        Payment payment_2 = paymentRepository.findOne(paymentUuid_2);
        log.info("Payment_2: {}", payment_2);
        Assert.assertNotNull(payment_2);
    }

    private void createTestData() {
        CreateAccountCommand command = new CreateAccountCommand("123", BigDecimal.ZERO);
        accountCommandService.createAccount(command);
    }
}
