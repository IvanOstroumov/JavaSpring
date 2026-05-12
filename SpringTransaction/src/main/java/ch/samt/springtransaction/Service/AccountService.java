package ch.samt.springtransaction.Service;

import ch.samt.springtransaction.Data.AccountRepository;
import ch.samt.springtransaction.Domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transfer(String account1Id, String account2Id, int amount) {
        Account account1 = accountRepository.findAccountById(account1Id);
        Account account2 = accountRepository.findAccountById(account2Id);

        account1.setBalance(account1.getBalance() - amount);
        accountRepository.save(account1);

        if(true){
            throw new RuntimeException("Any error");
        }

        account2.setBalance(account2.getBalance() + amount);
        accountRepository.save(account2);
    }
}
