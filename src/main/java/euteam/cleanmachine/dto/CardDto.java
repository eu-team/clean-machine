package euteam.cleanmachine.dto;
import euteam.cleanmachine.model.user.AuthItem;
import euteam.cleanmachine.model.user.NFCCard;

public class CardDto {

    private String cardNumber;
    private String fullName;
    private  String cvv;
    private String dateOfExpiry;

    public String getCardNumber(){return cardNumber;}

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
}
