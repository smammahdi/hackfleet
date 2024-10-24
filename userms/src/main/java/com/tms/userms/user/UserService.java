package com.tms.userms.user;

import com.tms.userms.user.dto.UserDTO;

public interface UserService 
{
    UserDTO getUserDTO(Long userId);
    boolean addBalance(Long userId, Long balance);
    boolean reduceBalance(Long userId, Long balance);
}
