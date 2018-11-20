package euteam.cleanmachine.dto;
import euteam.cleanmachine.model.user.AuthItem;
import euteam.cleanmachine.model.user.NFCCard;

import java.util.List;

public class NFCDto {

    private String cardNumber;

    public String getCardNumber(){return cardNumber;}

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}
