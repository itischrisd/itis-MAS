package model.invoice;

import java.math.BigDecimal;

public abstract class Balance {

    private static BigDecimal amount;

    public static BigDecimal getAmount() {
        return amount;
    }

    public static void setAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }
        Balance.amount = amount;
    }

    public static void addAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }
        Balance.amount = Balance.amount.add(amount);
    }

    public static void subtractAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }
        Balance.amount = Balance.amount.subtract(amount);
    }
}
