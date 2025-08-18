package dev.sara;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.sara.models.Account;

public class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1000f, 12f);
    }

    @Test
    void testDeposit() {
        Account account = new Account(1000.0f, 12.0f);
        account.deposit(500.0f);
        assertThat((double) account.getBalance(), is(closeTo(1500.0, 0.1))); 
        assertThat(account.getDepositCount(), is(equalTo(1)));
    }

    @Test
    void testWithdraw() {
        boolean success = account.withdraw(250f);
        assertTrue(success);
        assertThat((double) account.getBalance(), is(closeTo(750.0, 0.1)));
        assertThat(account.getWithdrawalCount(), is(equalTo(1)));

    }

    
    
    

}
