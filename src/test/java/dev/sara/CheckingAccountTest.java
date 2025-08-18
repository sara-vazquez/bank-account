package dev.sara;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.sara.models.CheckingAccount;

public class CheckingAccountTest {
    private CheckingAccount account;

    @BeforeEach
    void setUp() {
        account = new CheckingAccount(500f, 12f);
    }

    @Test
    void testWithdrawWithOverdraft() {
        boolean success = account.withdraw(600f);
        assertTrue(success);
        assertThat((double)account.getBalance(), is(closeTo(0f, 0.01f)));
        assertThat((double)account.getOverdraft(), is(closeTo(100f, 0.01f)));
        assertThat(account.getWithdrawalCount(), is(equalTo(1)));

    }
}
