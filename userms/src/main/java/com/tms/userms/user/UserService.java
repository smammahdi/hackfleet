package com.tms.userms.user;

import com.tms.userms.user.dto.UserDTO;

public interface UserService 
{
    UserDTO getUserDTO(String email);
}
