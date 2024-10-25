package com.tms.paymentms.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tms.paymentms.payment.dto.PaymentBodyDTO;
import com.tms.paymentms.payment.dto.TicketDTO;
import com.tms.paymentms.payment.dto.UserDTO;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PaymentControllerTest 
{
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreatePayment_Success() throws Exception {
        PaymentBodyDTO paymentBodyDTO = new PaymentBodyDTO();
        paymentBodyDTO.setUserId(1L);
        paymentBodyDTO.setBookingId(2L);
        paymentBodyDTO.setPassengerList(Arrays.asList("Passenger1", "Passenger2"));

        Payment newPayment = new Payment();
        when(paymentService.createPayment(any(Long.class), any(Long.class), any(List.class)))
                .thenReturn(newPayment);

        mockMvc.perform(post("/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentBodyDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testCreatePayment_BadRequest() throws Exception {
        PaymentBodyDTO paymentBodyDTO = new PaymentBodyDTO();
        paymentBodyDTO.setUserId(1L);
        paymentBodyDTO.setBookingId(2L);

        when(paymentService.createPayment(any(Long.class), any(Long.class), any(List.class)))
                .thenThrow(new RuntimeException("Bad request"));

        mockMvc.perform(post("/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentBodyDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTicketByUser_Success() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);

        TicketDTO ticket1 = new TicketDTO();
        ticket1.setTicketId(1L);
        TicketDTO ticket2 = new TicketDTO();
        ticket2.setTicketId(2L);

        List<TicketDTO> ticketList = Arrays.asList(ticket1, ticket2);

        when(paymentService.getTicketsByUser(1L)).thenReturn(ticketList);

        mockMvc.perform(get("/payment/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ticketId").value(1L))
                .andExpect(jsonPath("$[1].ticketId").value(2L));
    }

    @Test
    public void testGetTicketByUser_NotFound() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);

        when(paymentService.getTicketsByUser(1L)).thenReturn(null);

        mockMvc.perform(get("/payment/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteTicket_Success() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);

        when(paymentService.deleteTicket(1L, 1L)).thenReturn(true);

        mockMvc.perform(delete("/payment/ticket/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Ticket deletion was successful"));
    }

    @Test
    public void testDeleteTicket_NotFound() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);

        when(paymentService.deleteTicket(1L, 1L)).thenReturn(false);

        mockMvc.perform(delete("/payment/ticket/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isNotFound());
    }
}
