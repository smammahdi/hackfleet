package com.tms.paymentms.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.paymentms.payment.dto.PaymentBodyDTO;

@RestController
@RequestMapping("/payment")
public class PaymentController 
{
    private final PaymentService paymentService;

    public PaymentController (PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentBodyDTO payment){
        try{
            Payment newPayment = paymentService.createPayment(payment.getUserId(), payment.getBookingId(), payment.getPassengerList());
            return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
