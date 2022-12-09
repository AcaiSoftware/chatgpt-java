package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:58
 */
public interface Conversation {

    Response sendMessage(String message);

    AsyncPlaceholder<Response> sendMessageAsync(String message);

}
