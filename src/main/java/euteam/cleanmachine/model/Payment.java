package euteam.cleanmachine.model;

import javax.persistence.*;

@Entity
public abstract class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double amount;

}
