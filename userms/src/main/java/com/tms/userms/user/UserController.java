package com.tms.userms.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.userms.user.dto.UserDTO;


@RestController
@RequestMapping("/users")
public class UserController 
{
    private final UserService userService;

    public UserController(UserService userService) 
    {
        this.userService = userService;
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getUserInfo() 
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDTO userDTO = userService.getUserDTO(email);

        if(userDTO == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    
    
}
