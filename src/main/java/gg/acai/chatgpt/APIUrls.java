package gg.acai.chatgpt;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 18:13
 */
public enum APIUrls {
    URL("https://chat.openai.com/backend-api"),
    CONVERSATION_URL(URL + "/conversation");

    private final String url;

    APIUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
