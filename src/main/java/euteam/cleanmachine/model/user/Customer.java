package euteam.cleanmachine.model.user;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Customer extends User {
    @OneToOne
    private Account account;
}
