package com.tms.paymentms.payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Long userId, Long bookingId, List<String> passengerList);
}
