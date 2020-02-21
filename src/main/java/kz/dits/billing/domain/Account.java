package kz.dits.billing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
 * Created by ryessekeyev on 11/28/2016.
 */
@Entity
@Table(name = "its_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @SequenceGenerator(name = "account_generator", sequenceName = "account_sequence", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String accountNumber;
    private BigDecimal balance;
    @CreatedDate
    @Column(nullable = false)
    private Instant createdDate = Instant.now();

    public Account(String accountNumber, BigDecimal balance, Instant createdDate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", accountNumber='" + accountNumber + '\'' +
            ", balance=" + balance +
            ", createdDate=" + createdDate +
            '}';
    }
}
