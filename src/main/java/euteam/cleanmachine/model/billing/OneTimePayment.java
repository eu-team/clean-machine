package euteam.cleanmachine.model.billing;

import javax.persistence.Entity;

@Entity
public class OneTimePayment extends Payment {

    double cost;

    public  OneTimePayment (double cost){
        this.cost = cost;
    }
    public OneTimePayment(){}

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
