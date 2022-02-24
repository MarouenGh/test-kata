package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Client {

    @Id
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)

    private Account account;

        public Client(Long id, String name, Account account) {
            super();
            this.id = id;
            this.name = name;
            this.account = account;
        }

    public Client() {}

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }
}
