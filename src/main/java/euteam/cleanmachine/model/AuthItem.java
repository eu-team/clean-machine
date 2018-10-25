package euteam.cleanmachine.model;

import javax.persistence.*;

@Entity
public abstract class AuthItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
