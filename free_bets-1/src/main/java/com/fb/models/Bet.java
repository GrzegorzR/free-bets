package com.fb.models;

import javax.persistence.*;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public BetUser getBetUser() {
        return betUser;
    }

    public void setBetUser(BetUser betUser) {
        this.betUser = betUser;
    }

    public Long getOption() {
        return option;
    }

    public void setOption(Long option) {
        this.option = option;
    }

    @ManyToOne
    private BetUser betUser;

    private Long option;

    public Bet() {
    }

    @ManyToOne
    private BetDefinition betDefinition;

    private Double amount;

    public Bet(BetUser betUser, Long option, BetDefinition betDefinition, Double amount) {
        this.betUser = betUser;
        this.option = option;
        this.betDefinition = betDefinition;
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BetDefinition getBetDefinition() {
        return betDefinition;
    }

    public void setBetDefinition(BetDefinition betDefinition) {
        this.betDefinition = betDefinition;
    }
}
