package dev.sara.models;

public class CheckingAccount extends Account {
    private float overdraft = 0f;

    public CheckingAccount(float initialBalance, float annualRate) {
        super(initialBalance, annualRate);
    }

    @Override
    public boolean withdraw(float amount) {
        if (amount <= 0) throw new IllegalArgumentException("El retiro debe ser positivo");
        if (amount <= balance) {
            balance -= amount;
        } else {
            float deficit = amount - balance;
            balance = 0f;
            overdraft += deficit;
        }
        withdrawalCount++;
        return true;
    }

    @Override
    public void deposit(float amount) {
        if (amount <= 0) throw new IllegalArgumentException("El depósito debe ser positivo");
        if (overdraft > 0) {
            float appliedToOverdraft = Math.min(amount, overdraft);
            overdraft -= appliedToOverdraft;
            float remaining = amount - appliedToOverdraft;
            if (remaining > 0) balance += remaining;
        } else {
            balance += amount;
        }
        depositCount++;
    }

    @Override
    public void monthlyStatement() {
        super.monthlyStatement();
    }

    public float getOverdraft() { return overdraft; }

    @Override
    public String print() {
        int transactions = depositCount + withdrawalCount;
        return String.format(
            "[Corriente] Saldo: %.2f | Comisión mensual: %.2f | Transacciones: %d | Sobregiro: %.2f",
            balance, 0f, transactions, overdraft
        );
    }
}