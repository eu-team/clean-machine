package euteam.cleanmachine.model.user;

import euteam.cleanmachine.model.user.AuthItem;

import javax.persistence.Entity;

@Entity
public class NFCCard extends AuthItem {
    private String cardNumber;
    private String fullName;
    private  String cvv;
    private String dateOfExpiry;

    public NFCCard(String cardNumber,String fullName, String cvv, String dateOfExpiry) {
        this.cardNumber = cardNumber;
        this.fullName = fullName;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCvv() {
        return cvv;
    }
    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
}
