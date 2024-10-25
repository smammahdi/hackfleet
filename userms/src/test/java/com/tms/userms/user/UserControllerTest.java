package com.tms.userms.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.tms.userms.user.dto.UserDTO;

@SpringBootTest
public class UserControllerTest 
{
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testAddBalance() throws Exception {
        // Mock successful balance update
        when(userService.addBalance(anyLong(), anyLong())).thenReturn(true);

        mockMvc.perform(put("/users/balance/add")
                        .param("userId", "1")
                        .param("balance", "500"))
                .andExpect(status().isOk())
                .andExpect(content().string("Balance updated successfully"));
    }

    @Test
    public void testGetUserInfo() throws Exception 
    {
        // Mock UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setMoney(1000L);

        // Mock service call
        when(userService.getUserDTO(anyLong())).thenReturn(userDTO);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"money\":1000}"));
    }

    @Test
    public void testReduceBalance() throws Exception
    {
        // Mock successful balance reduction
        when(userService.reduceBalance(anyLong(), anyLong())).thenReturn(true);

        mockMvc.perform(put("/users/balance/reduce")
                        .param("userId", "1")
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(content().string("Balance updated successfully"));
    }
}