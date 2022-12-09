package gg.acai.chatgpt;

import gg.acai.acava.annotated.Use;
import gg.acai.acava.scheduler.AsyncPlaceholder;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 18:27
 */
@Use("Use ChatGPT#newBuilder to create a ChatGPT instance")
public interface ChatGPT {

    static ChatGPTBuilder newBuilder() {
        return new ChatGPTBuilder();
    }

    Conversation createConversation();

    String getSessionToken();

    AsyncPlaceholder<String> refreshAccessToken();

    String getAccessToken();

}
