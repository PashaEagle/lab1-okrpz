package io.crypto.beer.telegram.bot.integration.eos.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "blockchain")
public class EosBlockchainProperties {

    private String uri;
    private Path path;
    private Timeout timeout;

    @Getter
    @Setter
    @NoArgsConstructor
    @ConfigurationProperties(prefix = "path")
    public static class Path {
        private String history;
        private String balance;
        private String transaction;
        private Withdraw withdraw;

        @Getter
        @Setter
        @NoArgsConstructor
        @ConfigurationProperties(prefix = "withdraw")
        public static class Withdraw {
            private String getInfo;
            private String getBlock;
            private String abiJsonToBin;
            private String pushTransaction;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ConfigurationProperties(prefix = "timeout")
    public static class Timeout {
        private int connect;
        private int read;
    }
}
