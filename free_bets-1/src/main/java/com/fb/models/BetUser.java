package com.fb.models;

import javax.persistence.*;

@Entity
public class BetUser {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String login;
    private String password;
    private String role;
    private Double money;


    public BetUser( String login, String password, String role, Double money) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.money = money;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    protected BetUser() {}
	

}
