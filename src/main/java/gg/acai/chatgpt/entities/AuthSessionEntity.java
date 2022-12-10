package gg.acai.chatgpt.entities;

/**
 * Entity for the authentication session.
 *
 * @author Kaiser
 * @since 09/12/2022 18:31
 */
public class AuthSessionEntity {

    private UserEntity user;
    private String expires;
    private String accessToken;

    /**
     * Gets the user.
     *
     * @return The user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Gets the expiry time.
     *
     * @return The user
     */
    public String getExpires() {
        return expires;
    }

    /**
     * Gets the access token.
     *
     * @return The access token
     */
    public String getAccessToken() {
        return accessToken;
    }
}
