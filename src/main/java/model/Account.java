package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    private Long id;

    private Double balance;

    @OneToOne(fetch = FetchType.LAZY)
    private Client client;


    public Account(Long id, Double balance) {
        super();
        this.id = id;
        this.balance = balance;
    }

    public Account() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
