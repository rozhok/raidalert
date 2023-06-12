package com.devlify.raidalert.tuya;

import org.junit.jupiter.api.Test;

class TuyaSwitchTest {

    @Test
    public void testOn() {
        TuyaSwitch tuyaSwitch = new TuyaSwitch("", new TuyaToken("", ""));
        tuyaSwitch.on();
        tuyaSwitch.off();
    }

}