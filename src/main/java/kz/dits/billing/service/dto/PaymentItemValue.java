package kz.dits.billing.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kz.dits.billing.domain.util.InstantDeserializer;
import kz.dits.billing.domain.util.InstantSerializer;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by ryessekeyev on 3/3/2017.
 */
@Value
public class PaymentItemValue {

    @NonNull private String uuid;
    @NonNull private BigDecimal amount;
    @NonNull private String passageUuid;
    @JsonSerialize(using = InstantSerializer.class)
    @JsonDeserialize(using = InstantDeserializer.class)
    @NonNull private Instant passageDate;
    private String roadSectorCode;
    @NonNull private String roadSectorName;
}
