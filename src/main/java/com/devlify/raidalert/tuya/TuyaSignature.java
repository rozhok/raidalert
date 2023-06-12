package com.devlify.raidalert.tuya;

public class TuyaSignature {

    private static final String EMPTY_BODY_HASH = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    private final String method;
    private final String body;
    private final String time;
    private final String clientId;
    private final String clientSecret;
    private final String url;
    private final String token;

    public TuyaSignature(String method, String body, String time, String clientId, String clientSecret, String url, String token) {
        this.method = method;
        this.body = body;
        this.time = time;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.url = url;
        this.token = token;
    }

    public TuyaSignature(String method, String body, String time, String clientId, String clientSecret, String url) {
        this(method, body, time, clientId, clientSecret, url, null);
    }

    public String signature() {
        String bodyHash = body == null ? EMPTY_BODY_HASH : Sha256.hash(body).toLowerCase();
        String stringToSign = clientId + (token == null ? "" : token) + time + method + "\n" + bodyHash + "\n\n" + url;
        return Sha256.sign(stringToSign, clientSecret);
    }
}
