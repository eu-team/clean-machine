package euteam.cleanmachine.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Customer extends User{
    @OneToOne
    private Account account;
}
