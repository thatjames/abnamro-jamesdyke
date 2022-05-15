package xyz.slimjim.hungrytales.common.auth;

import xyz.slimjim.hungrytales.common.dataobject.DataObject;

public class LoginRequest extends DataObject {

    private String username;
    private byte[] password;
    private String challenge;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }
}
