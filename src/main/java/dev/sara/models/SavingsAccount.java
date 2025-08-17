package dev.sara.models;

public class SavingsAccount extends Account {
    private boolean active;

    public SavingsAccount(float initialBalance, float annualRate) {
        super(initialBalance, annualRate);
        this.active = initialBalance >= 10000f;
    }

    private void updateStatus() {
        this.active = balance >= 10000f;
    }

    @Override
    public void deposit(float amount) {
        updateStatus();
        if (!active) throw new IllegalStateException("La cuenta de ahorros está inactiva; no se permite consignar");
        super.deposit(amount);
        updateStatus();
    }

    @Override
    public boolean withdraw(float amount) {
        updateStatus();
        if (!active) return false;
        boolean ok = super.withdraw(amount);
        updateStatus();
        return ok;
    }

    @Override
    public void monthlyStatement() {
        int extraWithdrawals = Math.max(0, withdrawalCount - 4);
        if (extraWithdrawals > 0) {
            monthlyFee += extraWithdrawals * 1000f;
        }
        super.monthlyStatement();
        updateStatus();
    }

    public boolean isActive() { return active; }

    @Override
    public String print() {
        int transactions = depositCount + withdrawalCount;
        return String.format(
            "[Ahorros] Saldo: %.2f | Comisión mensual: %.2f | Transacciones: %d | Activa: %b",
            balance, 0f, transactions, active
        );
    }
}