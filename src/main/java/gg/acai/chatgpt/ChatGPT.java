package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.request.ChatGPTRequest;
import gg.acai.chatgpt.request.StandardChatGPTRequest;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 17:47
 */
public class ChatGPT implements Conversation {

    private static ChatGPT instance;
    private final String sessionToken;

    public ChatGPT(String sessionToken) {
        instance = this;
        this.sessionToken = sessionToken;
    }

    @Override
    public AsyncPlaceholder<Response> sendMessage(String message) {
        return Schedulers.supplyAsync(() -> {
            // @kaiser
            ChatGPTRequest request = StandardChatGPTRequest.newBuilder()
                    .build();
            return null;
        });
    }
}
