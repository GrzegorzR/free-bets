package com.fb.models;

import javax.persistence.*;

@Entity
public class BetDefinition {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public BetDefinition() {
    }

    public BetDefinition(BetUser owner, String description,
                         String optionOne, String optionTwo,
                         Boolean resolved, Integer winOption) {
        this.owner = owner;
        this.description = description;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.resolved = resolved;
        this.winOption = winOption;
    }

    @ManyToOne
    private BetUser owner;

    private String description;

    private String optionOne;

    private String optionTwo;

    private Boolean resolved;

    private Integer winOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BetUser getOwner() {
        return owner;
    }

    public void setOwner(BetUser owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public Integer getWinOption() {
        return winOption;
    }

    public void setWinOption(Integer winOption) {
        this.winOption = winOption;
    }
}
