package gg.acai.chatgpt;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 18:27
 */
public interface ChatGPT {

    Conversation createConversation();

    String getSessionToken();

}
