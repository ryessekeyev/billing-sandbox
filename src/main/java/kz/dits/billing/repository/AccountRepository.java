package kz.dits.billing.repository;

import kz.dits.billing.domain.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by ryessekeyev on 11/28/2016.
 */
public interface AccountRepository extends AccountBaseRepository<Account> {

    @Modifying
    @Transactional
    @Query("update Account a set a.balance = :balance where a.id = :accountId")
    int setBalanceFor(@Param("balance") BigDecimal balance, @Param("accountId") Long accountId);

}
