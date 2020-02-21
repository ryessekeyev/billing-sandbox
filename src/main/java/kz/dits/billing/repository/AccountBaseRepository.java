package kz.dits.billing.repository;

import kz.dits.billing.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface AccountBaseRepository<T extends Account> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    T findOneByAccountNumber(@Param("accountNumber") String accountNumber);
}
