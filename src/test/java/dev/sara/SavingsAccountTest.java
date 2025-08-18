package dev.sara;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
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
}
