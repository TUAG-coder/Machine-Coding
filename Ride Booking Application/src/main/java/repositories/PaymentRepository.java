package repositories;

import models.Payment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {
    private Map<Long, Payment> paymentMap = new HashMap<>();

    public void createPayment(Payment payment) {
        Long id = (long) paymentMap.size();
        payment.setId(id);
        payment.setCreatedAt(new Date());
        paymentMap.put(id, payment);
    }
}
