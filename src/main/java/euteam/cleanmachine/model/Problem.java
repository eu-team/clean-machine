package euteam.cleanmachine.model;

import euteam.cleanmachine.model.enums.ProblemStatus;

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
