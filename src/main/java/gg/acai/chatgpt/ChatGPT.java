package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 17:47
 */
public class ChatGPT {

    private static ChatGPT instance;
    private final String sessionToken;

    public ChatGPT(String sessionToken) {
        instance = this;

        this.sessionToken = sessionToken;
    }

    public AsyncPlaceholder<String> sendMessage(String message) {
        return Schedulers.supplyAsync(() -> {
            // what do we send?
            return null;
        });
    }

}
