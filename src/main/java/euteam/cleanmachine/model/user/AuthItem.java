package euteam.cleanmachine.model.user;

import javax.persistence.*;

@Entity
public abstract class AuthItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
