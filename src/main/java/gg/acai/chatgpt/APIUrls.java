package gg.acai.chatgpt;

/**
 * Common API Urls for the ChatGPT API.
 *
 * @author Clouke
 * @since 09.12.2022 18:13
 */
public enum APIUrls {
    /**
     * Represents the URL for the OpenAI API.
     */
    API_URL("https://chat.openai.com"),

    /**
     * Represents the URL for the OpenAI API conversation.
     */
    CONVERSATION_URL(API_URL.getUrl() + "/backend-api/conversation"),

    /**
     * Represents the URL for authenticating with the OpenAI API.
     */
    REFRESH_TOKEN_URL(API_URL.getUrl() + "/api/auth/session");

    private final String url;

    APIUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
