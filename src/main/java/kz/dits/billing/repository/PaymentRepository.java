package kz.dits.billing.repository;

import kz.dits.billing.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by rustem on 2/10/2017.
 */
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

}
