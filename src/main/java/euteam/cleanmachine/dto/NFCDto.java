package euteam.cleanmachine.dto;
import euteam.cleanmachine.model.user.AuthItem;
import euteam.cleanmachine.model.user.NFCCard;

import java.util.List;

public class NFCDto {

    private String cardNumber;
    private List<AuthItem> authItemList;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber(){return cardNumber;}

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<AuthItem> getAuthItemList() {
        return authItemList;
    }

    public void setAuthItemList(List<AuthItem> authItemList) {
        this.authItemList = authItemList;
    }

}
