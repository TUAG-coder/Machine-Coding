package enums;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentMode {
    CASH,
    UPI,
    CREDIT_CARD,
    DEBIT_CARD;

    public static Optional<PaymentMode> getPaymentMode(String paymentMode) {
        return Arrays.stream(PaymentMode.values())
                .filter(mode -> mode.name().equalsIgnoreCase(paymentMode))
                .findFirst();
    }
}
