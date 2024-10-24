package com.tms.userms.user.dto;

public class UserDTO 
{
    private Long userId;
    private Long nid;
    private String name;
    private String email;
    private Long money;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public Long getNid() {
        return nid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getMoney() {
        return money;
    }
}
