package euteam.cleanmachine.model.user;

import javax.persistence.*;

@Entity
public abstract class AuthItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
