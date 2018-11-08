package euteam.cleanmachine.model.facility;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Program> programs;

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public boolean containsProgram(Long programId){
        for (Program program:programs
             ) {
            if(program.getId()==programId)return true;
        }
        return false;
    }
}
