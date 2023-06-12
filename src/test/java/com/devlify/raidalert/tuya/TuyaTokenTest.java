package com.devlify.raidalert.tuya;

import org.junit.jupiter.api.Test;

class TuyaTokenTest {

    @Test
    public void testToken() {
        String clientId = "";
        String clientSecret = "";

        TuyaToken token = new TuyaToken(clientId, clientSecret);

        System.out.println(token.token());
    }

}