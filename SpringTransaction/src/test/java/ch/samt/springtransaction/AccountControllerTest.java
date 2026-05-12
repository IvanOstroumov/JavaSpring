package ch.samt.springtransaction;

import ch.samt.springtransaction.Data.AccountRepository;
import ch.samt.springtransaction.Domain.Account;
import ch.samt.springtransaction.Service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public void AccountControllerTest(AccountService accountService) {
        this.accountService = accountService;
    }

    @Test
    public void testTrasfer(){
        accountRepository.save(new Account("account1", 1000));
        accountRepository.save(new Account("account2", 0));

        try{
            accountService.transfer("account1", "account2", 600);
        } catch (Exception e){
            System.out.println("Si è verificato un errore!");
            assertEquals(1000, accountRepository.findAccountById("account1").getBalance());
            assertEquals(0, accountRepository.findAccountById("account2").getBalance());
            return;
        }
        assertEquals(400, accountRepository.findAccountById("account1").getBalance());
        assertEquals(600, accountRepository.findAccountById("account2").getBalance());
    }
}
