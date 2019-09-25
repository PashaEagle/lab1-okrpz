package io.crypto.beer.telegram.bot.session;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EditState {

    public EditState() {
        cleanEditStates();
    }
    
    private boolean accountName;
    
    private boolean profileFullName;
    
    private boolean profilePhone;
    
    private boolean addressCountry;
    
    private boolean addressCity;
    
    private boolean addressStreet;
    
    private Integer numberOfAddress;
    
    public void clickAccountName() {
        cleanEditStates();
        this.accountName = true;
    }
    
    public void clickProfileFullName() {
        cleanEditStates();
        this.profileFullName = true;
    }
    
    public void clickProfilePhone() {
        cleanEditStates();
        this.profilePhone = true;
    }
    
    public void clickProfileAddressCountry() {
        cleanEditStates();
        this.addressCountry = true;
    }
    
    public void clickProfileAddressCity() {
        cleanEditStates();
        this.addressCity = true;
    }
    
    public void clickProfileAddressStreet() {
        cleanEditStates();
        this.addressStreet = true;
    }
    
    public void clickNumberOfAddress(Integer number) {
    	this.numberOfAddress = number;
    }
    
    public void cleanNumberOfAddress() {
    	this.numberOfAddress = null;
    }
    
    public void cleanEditStates() {
        this.profileFullName = false;
        this.profilePhone = false;
        this.addressCountry = false;
        this.addressCity = false;
        this.addressStreet = false;
        this.accountName = false;
    }
    
}
