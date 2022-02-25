package model;

import enums.OperationType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transaction {
    @Id
    private Long id;
    private LocalDateTime date;
    private OperationType operationType;
    private Double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    public Transaction(LocalDateTime date, OperationType operationType, Double amount, Account account) {
        super();
        this.date = date;
        this.operationType = operationType;
        this.amount = amount;
        this.account = account;
    }

    public Transaction() {}



    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", operationType=" + operationType +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(date, that.date) && operationType == that.operationType && Objects.equals(amount, that.amount) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, operationType, amount, account);
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


}
