package gg.acai.chatgpt;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 18:13
 */
public enum APIUrls {
    API_URL("https://chat.openai.com"),
    CONVERSATION_URL(API_URL.getUrl() + "/backend-api/conversation"),
    REFRESH_TOKEN_URL(API_URL.getUrl() + "/api/auth/session");

    private final String url;

    APIUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
