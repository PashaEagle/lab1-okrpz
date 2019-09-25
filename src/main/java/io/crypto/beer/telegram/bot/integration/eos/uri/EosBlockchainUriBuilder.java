package io.crypto.beer.telegram.bot.integration.eos.uri;

import io.crypto.beer.telegram.bot.integration.eos.properties.EosBlockchainProperties;
import io.crypto.beer.telegram.bot.integration.properties.WalletProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class EosBlockchainUriBuilder {

    private final EosBlockchainProperties eosBlockchainProperties;
    private final WalletProperties walletProperties;

    public URI getBalanceUri() {
        return UriComponentsBuilder.fromUriString(eosBlockchainProperties.getUri())
                .path(eosBlockchainProperties.getPath().getBalance())
                .build()
                .encode()
                .toUri();
    }

    public URI getHistoryUri() {
        return UriComponentsBuilder.fromUriString(eosBlockchainProperties.getUri())
                .path(eosBlockchainProperties.getPath().getHistory())
                .build()
                .encode()
                .toUri();
    }

    public URI getTransactionUri() {
        return UriComponentsBuilder.fromUriString(eosBlockchainProperties.getUri())
                .path(eosBlockchainProperties.getPath().getTransaction())
                .build()
                .encode()
                .toUri();
    }

    public URI getWithdrawGetInfoUri() {
        return UriComponentsBuilder.fromUriString(eosBlockchainProperties.getUri())
                .path(eosBlockchainProperties.getPath().getWithdraw().getGetInfo())
                .build()
                .encode()
                .toUri();
    }

    public URI getWithdrawGetBlockUri() {
        return UriComponentsBuilder.fromUriString(eosBlockchainProperties.getUri())
                .path(eosBlockchainProperties.getPath().getWithdraw().getGetBlock())
                .build()
                .encode()
                .toUri();
    }

    public URI getWithdrawAbiJsonToBinUri() {
        return UriComponentsBuilder.fromUriString(eosBlockchainProperties.getUri())
                .path(eosBlockchainProperties.getPath().getWithdraw().getAbiJsonToBin())
                .build()
                .encode()
                .toUri();
    }

    public URI getWithdrawGetPushTransactionUri() {
        return UriComponentsBuilder.fromUriString(eosBlockchainProperties.getUri())
                .path(eosBlockchainProperties.getPath().getWithdraw().getPushTransaction())
                .build()
                .encode()
                .toUri();
    }
    
    public URI getWalletOpenUri() {
    	return UriComponentsBuilder.fromUriString(walletProperties.getUri())
    			.path(walletProperties.getPath().getOpen())
    			.build()
    			.encode()
    			.toUri();
    }
    
    public URI getWalletUnlockUri() {
    	return UriComponentsBuilder.fromUriString(walletProperties.getUri())
    			.path(walletProperties.getPath().getUnlock())
    			.build()
    			.encode()
    			.toUri();
    }
    
    public URI getWalletCreateKeyUri() {
    	return UriComponentsBuilder.fromUriString(walletProperties.getUri())
    			.path(walletProperties.getPath().getCreateKey())
    			.build()
    			.encode()
    			.toUri();
    }
    
    public URI getWalletSignTransactionUri() {
    	return UriComponentsBuilder.fromUriString(walletProperties.getUri())
    			.path(walletProperties.getPath().getSignTransaction())
    			.build()
    			.encode()
    			.toUri();
    }

}
