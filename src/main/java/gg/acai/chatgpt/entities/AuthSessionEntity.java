package gg.acai.chatgpt.entities;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Kaiser
 * @since 09/12/2022 18:31
 */
public class AuthSessionEntity {

    private UserEntity user;
    private String expires;
    private String accessToken;

    public UserEntity getUser() {
        return user;
    }

    public String getExpires() {
        return expires;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
