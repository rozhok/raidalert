package com.devlify.raidalert.tuya;

public class TuyaTokenResponse {

    private boolean success;
    private TuyaTokenResult result;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public TuyaTokenResult getResult() {
        return result;
    }

    public void setResult(TuyaTokenResult result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
