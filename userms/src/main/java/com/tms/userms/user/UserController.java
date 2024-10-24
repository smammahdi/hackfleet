package com.tms.userms.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable Long id) 
    {
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String email = authentication.getName();

        UserDTO userDTO = userService.getUserDTO(id);

        if(userDTO == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    
    @PutMapping("/balance/add")
    public ResponseEntity<String> addBalance(@RequestParam Long userId, @RequestParam Long balance) 
    {
        if(userService.addBalance(userId, balance))
        {
            return new ResponseEntity<>("Balance updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update balance", HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping("/balance/reduce")
    public ResponseEntity<String> reduceBalance(@RequestParam Long userId, @RequestParam Long balance) 
    {
        if(userService.reduceBalance(userId, balance))
        {
            return new ResponseEntity<>("Balance updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update balance", HttpStatus.BAD_REQUEST);
    }
}
