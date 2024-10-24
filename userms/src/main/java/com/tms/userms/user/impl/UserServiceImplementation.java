package com.tms.userms.user.impl;

import org.springframework.stereotype.Service;

import com.tms.userms.user.User;
import com.tms.userms.user.UserRepository;
import com.tms.userms.user.UserService;
import com.tms.userms.user.dto.UserDTO;
import com.tms.userms.user.mapper.UserMapper;

@Service
public class UserServiceImplementation implements UserService
{
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) 
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserDTO(Long userId) 
    {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null)
        {
            return null;
        }
        UserDTO userDTO = UserMapper.mapToUserDTO(user);
        return userDTO;
    }

    @Override
    public boolean addBalance(Long userId, Long balance) 
    {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null)
        {
            return false;
        }
        user.setMoney(user.getMoney() + balance);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean reduceBalance(Long userId, Long balance) 
    {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null || user.getMoney() < balance)
        {
            return false;
        }
        user.setMoney(user.getMoney() - balance);
        userRepository.save(user);
        return true;
    }
}
