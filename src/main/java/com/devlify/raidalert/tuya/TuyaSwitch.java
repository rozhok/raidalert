package com.devlify.raidalert.tuya;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class TuyaSwitch {

    private final String deviceId;
    private final TuyaToken token;
    private boolean on = true;

    public TuyaSwitch(@Value("${TUYA_DEVICE_ID}") String deviceId, TuyaToken token) {
        this.deviceId = deviceId;
        this.token = token;
    }

    public void on() {
        manageDevice("{\"commands\":[{\"code\":\"switch_1\",\"value\":true}]}");
        on = true;
    }

    public void off() {
        manageDevice("{\"commands\":[{\"code\":\"switch_1\",\"value\":false}]}");
        on = false;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isOff() {
        return !on;
    }

    protected void manageDevice(String commands) {
        String time = String.valueOf(System.currentTimeMillis());
        String url = String.format("/v1.0/iot-03/devices/%s/commands", deviceId);
        String tokenStr = token.token();
        TuyaSignature signature = new TuyaSignature("POST", commands, time, token.clientId(), token.clientSecret(), url, tokenStr);

        WebClient webClient = WebClient.builder().build();

        Map response = webClient.post()
                .uri("https://openapi.tuyaeu.com" + url)
                .header("client_id", token.clientId())
                .header("access_token", token.token())
                .header("t", time)
                .header("sign_method", "HMAC-SHA256")
                .header("sign", signature.signature())
                .header("mode", "cors")
                .bodyValue(commands)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        System.out.println(response);
    }
}
