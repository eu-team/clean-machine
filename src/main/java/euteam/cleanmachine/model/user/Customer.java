package euteam.cleanmachine.model.user;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Customer extends User {

    public Customer() {
        super();
    }
}
