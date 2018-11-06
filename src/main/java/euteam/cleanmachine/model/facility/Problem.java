package euteam.cleanmachine.model.facility;

import euteam.cleanmachine.model.enums.ProblemStatus;
import euteam.cleanmachine.model.facility.machine.Machine;
import euteam.cleanmachine.model.user.Maintainer;

import javax.persistence.*;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private String description;

    @ManyToOne
    private Maintainer maintainer;

    @ManyToOne
    private Machine machine;

    private ProblemStatus problemStatus;
}
