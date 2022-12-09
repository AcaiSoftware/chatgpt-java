package gg.acai.chatgpt.types;

import gg.acai.acava.event.EventBus;
import gg.acai.chatgpt.AbstractConversation;
import gg.acai.chatgpt.ChatGPT;
import gg.acai.chatgpt.ComplexAccessCache;
import gg.acai.chatgpt.Conversation;

import java.util.Optional;

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

    public ChatGPTAPI(String sessionToken, EventBus eventBus) {
        instance = this;

        this.sessionToken = sessionToken;
        this.eventBus = eventBus;
        this.accessTokenCache = new ComplexAccessCache(this);
    }

    @Override
    public Conversation createConversation() {
        return new AbstractConversation();
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

    public static ChatGPTAPI getInstance() {
        return instance;
    }
}
