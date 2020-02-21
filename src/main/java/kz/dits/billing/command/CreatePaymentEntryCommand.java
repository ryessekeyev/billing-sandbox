package kz.dits.billing.command;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

/**
 * Created by rustem on 3/6/2017.
 */
@Value
public class CreatePaymentEntryCommand {

    @NonNull private String accountNumber;
    @NonNull private BigDecimal amount;
}
