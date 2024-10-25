package com.tms.paymentms.payment;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.paymentms.payment.dto.PaymentBodyDTO;
import com.tms.paymentms.payment.dto.TicketDTO;
import com.tms.paymentms.payment.dto.UserDTO;

@RestController
@RequestMapping("/payment")
public class PaymentController 
{
    private final PaymentService paymentService;

    public PaymentController (PaymentService paymentService)
    {
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

    @GetMapping("/ticket")
    public ResponseEntity<List<TicketDTO>> getTicketByUser(@RequestBody UserDTO userdto)
    {
        List<TicketDTO> ticketList = paymentService.getTicketsByUser(userdto.getUserId());
        if(ticketList == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        }
    }
    
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketDTO> getTicketByUserById(@RequestBody UserDTO userdto, @PathVariable Long ticketId)
    {
        TicketDTO ticket = paymentService.getTicketByUserById(userdto.getUserId(), ticketId);
        if(ticket == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }

    @DeleteMapping("/ticket/{ticketId}")
    public ResponseEntity<?> deleteTicket(@RequestBody UserDTO userdto,@PathVariable Long ticketId)
    {
        boolean isDeleted = paymentService.deleteTicket(userdto.getUserId(), ticketId);
        if(isDeleted)
        {
            return new ResponseEntity<>("Ticket deletion was succesful",HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
