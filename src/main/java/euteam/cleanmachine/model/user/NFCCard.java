package euteam.cleanmachine.model.user;

import javax.persistence.Entity;

@Entity
public class NFCCard extends AuthItem {

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
