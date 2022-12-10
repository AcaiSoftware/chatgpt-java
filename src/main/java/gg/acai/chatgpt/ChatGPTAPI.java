package gg.acai.chatgpt;

import gg.acai.acava.event.EventBus;
import okhttp3.OkHttpClient;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 17:47
 */
public final class ChatGPTAPI implements ChatGPT {

    private static ChatGPTAPI instance;
    private final String sessionToken;
    private final EventBus eventBus;
    private final ComplexAccessCache accessTokenCache;
    private final OkHttpClient client;

    public ChatGPTAPI(String sessionToken, EventBus eventBus) {
        instance = this;

        this.client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        this.sessionToken = sessionToken;
        this.eventBus = eventBus;
        this.accessTokenCache = new ComplexAccessCache(this);
    }

    @Override
    public Conversation createConversation() {
        return new AbstractConversation(this.client, UUID.randomUUID());
    }

    @Override
    public String getSessionToken() {
        return this.sessionToken;
    }

    @Override
    public String getAccessToken() {
        return this.accessTokenCache.get("accessToken");
    }

    @Override
    public Optional<EventBus> getEventBus() {
        return Optional.ofNullable(this.eventBus);
    }

    @Override
    public ComplexAccessCache getComplexAccessCache() {
        return this.accessTokenCache;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return this.client;
    }

    public static ChatGPTAPI getInstance() {
        return instance;
    }
}
