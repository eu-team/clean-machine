package euteam.cleanmachine.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class NFCCard extends AuthItem{
    private String cardNumber;

    public NFCCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
