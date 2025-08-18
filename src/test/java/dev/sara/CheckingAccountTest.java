package dev.sara;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
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

    @Test
    void testDeposit_LessThanOverdraft() {
        account.withdraw(600f);

        account.deposit(30f);
        assertThat((double)account.getBalance(), is(closeTo(0f, 0.01f)));
        assertThat((double)account.getOverdraft(), is(closeTo(70f, 0.01)));
    }

    @Test 
    void testReturnString() {
        account.withdraw(1100f);
        account.deposit(500f);
        
        String output = account.print();
        
        assertThat(output, containsString("[Corriente] Saldo: 0,00"));
        assertThat(output, containsString("Comisión mensual: 0,00"));
        assertThat(output, containsString("Transacciones: 2"));
        assertThat(output, containsString("Sobregiro: 100,00"));
    }
}
