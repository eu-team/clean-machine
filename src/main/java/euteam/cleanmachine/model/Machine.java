package euteam.cleanmachine.model;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Program> programs;
}
