package com.tms.bookingms.booking.external;

public class UserDTO
{
    private Long id;
    private Long nid;
    private String name;
    private String email;
    private Long money;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public Long getNid() {
        return this.nid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getMoney() {
        return this.money;
    }
}
