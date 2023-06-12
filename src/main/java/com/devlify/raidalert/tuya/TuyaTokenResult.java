package com.devlify.raidalert.tuya;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TuyaTokenResult {

    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
