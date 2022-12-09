package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.chatgpt.request.ChatGPTRequest;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:58
 */
public interface Conversation {

    Response sendMessage(ChatGPTRequest request);

    AsyncPlaceholder<Response> sendMessageAsync(ChatGPTRequest request);

}
