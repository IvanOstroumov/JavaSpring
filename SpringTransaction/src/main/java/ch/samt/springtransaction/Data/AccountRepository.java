package ch.samt.springtransaction.Data;

import ch.samt.springtransaction.Domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findAccountById(String id);
}
