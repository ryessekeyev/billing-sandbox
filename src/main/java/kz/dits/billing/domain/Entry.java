package kz.dits.billing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by rustem on 12/27/2016.
 */
@Entity
@Table(name = "its_entry")
@Data
@NoArgsConstructor
public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entry_generator")
    @SequenceGenerator(name = "entry_generator", sequenceName = "entry_sequence", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private Long id;

    private String entryType;

    @NonNull
    @NotNull
    @Column
    @JsonIgnore
    private Long accountId;

    private String accountNumber;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private BigDecimal amount;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdDate = Instant.now();

    public Entry(Long accountId, String accountNumber, BigDecimal amount, Instant createdDate, String entryType) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.createdDate = createdDate;
        this.entryType = entryType;
    }

    public Entry(Long id, Long accountId, String accountNumber, BigDecimal amount, Instant createdDate, String entryType) {
        this.id = id;
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.createdDate = createdDate;
        this.entryType = entryType;
    }

    @Override
    public String toString() {
        return "Entry{" +
            "id=" + id +
            ", entryType=" + entryType +
            ", accountId=" + accountId +
            ", accountNumber='" + accountNumber + '\'' +
            ", amount=" + amount +
            ", createdDate=" + createdDate +
            '}';
    }
}
