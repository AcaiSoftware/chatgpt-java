package gg.acai.chatgpt.types;

import gg.acai.chatgpt.AbstractConversation;
import gg.acai.chatgpt.ChatGPT;
import gg.acai.chatgpt.Conversation;
import kong.unirest.Config;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 17:47
 */
public class ChatGPTAPI implements ChatGPT {

    private static ChatGPTAPI instance;
    private final String sessionToken;
    private final Config config;

    public ChatGPTAPI(String sessionToken, Config config) {
        instance = this;

        this.sessionToken = sessionToken;
        this.config = config;
    }

    @Override
    public Conversation createConversation() {
        return new AbstractConversation();
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public static ChatGPTAPI getInstance() {
        return instance;
    }
}
