package kz.dits.billing.repository;

import kz.dits.billing.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rustem on 12/27/2016.
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
