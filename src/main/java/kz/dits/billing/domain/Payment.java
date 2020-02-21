package kz.dits.billing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by rustem on 2/9/2017.
 */
@Entity
@Table(name = "its_payment")
@Builder
@Getter @EqualsAndHashCode(exclude = {"uuid"})
@NoArgsConstructor @AllArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NonNull
    private UUID uuid;

    @JsonIgnore
    private Long accountId;

    private String accountNumber;

    @Builder.Default
    @CreatedDate
    @Column(nullable = false)
    private Instant createdDate = Instant.now();

    @NonNull
    private BigDecimal cash;

    @Column(name = "cash_change")
    private BigDecimal change;

    @Override
    public String toString() {
        return "Payment{" +
            "uuid=" + uuid +
            ", accountId=" + accountId +
            ", accountNumber='" + accountNumber + '\'' +
            ", createdDate=" + createdDate +
            ", cash=" + cash +
            ", change=" + change +
            '}';
    }
}
