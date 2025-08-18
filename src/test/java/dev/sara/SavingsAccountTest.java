package dev.sara;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.sara.models.SavingsAccount;

public class SavingsAccountTest {
    private SavingsAccount activeAccount;
    private SavingsAccount inactiveAccount;

    @BeforeEach
    void setUp() {
        activeAccount = new SavingsAccount(15000f, 12f);
        inactiveAccount = new SavingsAccount(5000f, 12f);
    }

    @Test
    void testActiveAccount() {
        assertTrue(activeAccount.isActive());
        assertThat((double) activeAccount.getBalance(), is(closeTo(15000f, 0.01f)));
    }

    @Test
    void testDepositSavingsAccount() {
        activeAccount.deposit(500f);
        assertThat((double)activeAccount.getBalance(), is(closeTo(15500f, 0.01f)));
        assertThat(activeAccount.getDepositCount(), is(equalTo(1)));
    }

    @Test
    void testDepositSavingsAccount_Inactive() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> inactiveAccount.deposit(10f));
        assertThat(exception.getMessage(), is(equalTo("La cuenta de ahorros está inactiva; no se permite consignar")));
    }

    @Test
    void testWithdrawShouldFail_Inactive() {
        boolean success = inactiveAccount.withdraw(100f);
        assertFalse(success);
        assertThat((double) inactiveAccount.getBalance(), is(closeTo(5000f, 0.01f)));
        assertThat(inactiveAccount.getWithdrawalCount(), is(equalTo(0)));
    }

    @Test
    void testMonthlyStatementAnd5Withdrawals() {
        SavingsAccount testAccount = new SavingsAccount(12000f, 12f);
        testAccount.withdraw(100f);
        testAccount.withdraw(100f);
        testAccount.withdraw(100f);
        testAccount.withdraw(100f);
        testAccount.withdraw(100f);

        testAccount.monthlyStatement();
        assertThat((double)testAccount.getBalance(), is(closeTo(10605f, 0.1f)));
    }
}
