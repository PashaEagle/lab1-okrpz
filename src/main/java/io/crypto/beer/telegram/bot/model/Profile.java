package io.crypto.beer.telegram.bot.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Profile {
        
	private Integer id;
	
    private String fullName;
    
    private String phone;
    
    private boolean bot;
    
    private Integer telegaId;
    
    private String userName;
    
    private String languageCode;
    
    private String referral;
    
    private String providedReferral;
    
    private String accountName;
    
}
