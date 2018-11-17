package euteam.cleanmachine.model.user;

import euteam.cleanmachine.model.billing.AccountSubscription;
import euteam.cleanmachine.model.billing.Payment;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    private double balance;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AccountSubscription> subscriptions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments;

    public Account() {
        // Empty default constructor
    }

    public Account(User user) {
        this.balance = 0;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<AccountSubscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<AccountSubscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void substractBalance(double payment){
        this.balance -= payment;
    }

    public void addPayment(Payment payment){
        payments.add(payment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", user=" + user.toString() + ", balance=" + balance + '}';
    }

}
