package com.devlify.raidalert.tuya;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TuyaToken {

    private static final String TOKEN_URL = "/v1.0/token?grant_type=1";
    private final String clientId;
    private final String clientSecret;

    public TuyaToken(@Value("${TUYA_CLIENT_ID}") String clientId, @Value("${TUYA_CLIENT_SECRET}") String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String token() {
        String time = String.valueOf(System.currentTimeMillis());
        TuyaSignature signature = new TuyaSignature("GET", null, time, clientId, clientSecret, TOKEN_URL);

        WebClient webClient = WebClient.builder().build();

        TuyaTokenResponse response = webClient.get()
                .uri("https://openapi.tuyaeu.com/v1.0/token?grant_type=1")
                .header("client_id", clientId)
                .header("secret", clientSecret)
                .header("t", time)
                .header("sign_method", "HMAC-SHA256")
                .header("sign", signature.signature())
                .retrieve()
                .bodyToMono(TuyaTokenResponse.class)
                .block();

        if (response.isSuccess()) {
            return response.getResult().getAccessToken();
        } else {
            throw new RuntimeException("Unable to refresh token: " + response.getMsg());
        }
    }

    public String clientId() {
        return clientId;
    }

    public String clientSecret() {
        return clientSecret;
    }

}
