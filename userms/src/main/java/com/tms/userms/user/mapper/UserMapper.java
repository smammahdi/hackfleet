package com.tms.userms.user.mapper;

import com.tms.userms.user.User;
import com.tms.userms.user.dto.UserDTO;

public class UserMapper 
{
    public static UserDTO mapToUserDTO(User user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNid(user.getNid());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setMoney(user.getMoney());

        return userDTO;
    }
}
