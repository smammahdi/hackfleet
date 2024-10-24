package com.tms.userms.user.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest 
{
    private Long nid;
    private String name;
    private String email;
    private String password;
    private Long money;
}
