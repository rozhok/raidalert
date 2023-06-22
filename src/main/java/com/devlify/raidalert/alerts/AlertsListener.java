package com.devlify.raidalert.alerts;

import com.devlify.raidalert.tuya.TuyaSwitch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AlertsListener {

    private static final Logger LOG = LoggerFactory.getLogger(AlertsListener.class);

    private final String alertsToken;
    private final TuyaSwitch tuyaSwitch;
    private boolean isAlert = false;

    public AlertsListener(@Value("${ALERTS_TOKEN}") String alertsToken, TuyaSwitch tuyaSwitch) {
        this.alertsToken = alertsToken;
        this.tuyaSwitch = tuyaSwitch;
    }

    @Scheduled(fixedDelay = 30000)
    public void checkAlert() {
        WebClient webClient = WebClient.builder().build();

        String response = webClient.get()
                .uri("https://api.alerts.in.ua/v1/iot/active_air_raid_alerts_by_oblast.json")
                .header("Authorization", "Bearer " + alertsToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response == null) {
            throw new RuntimeException("Alerts response shouldn't be null");
        }

        // remove quotes since API returns quotes
        boolean currentState = response.replace("\"",  "").charAt(9) == 'A';

        if (!isAlert && currentState) {
            // alert triggered
            LOG.info("Alert triggered. Switching power off");
            tuyaSwitch.off();
            isAlert = true;
        } else if (isAlert && !currentState) {
            // alert clear
            LOG.info("Alert clear. Switching power on");
            tuyaSwitch.on();
            isAlert = false;
        } else if (isAlert && currentState) {
            LOG.info("Alert is still going on");
            // nothing to do here, alert is going on
        } else if (!isAlert && !currentState) {
            // nothing to do here, no alert is going on
            LOG.info("No alert is going on");
        }
    }
}
