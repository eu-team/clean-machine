package euteam.cleanmachine.model.facility;

import javax.persistence.Entity;

@Entity
public class StandardProgram extends  Program {

    public StandardProgram(String name,double duration, double cost){
        super(name,duration,cost );
    }

}


