package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.chatgpt.request.ChatGPTRequest;

import java.util.Optional;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:58
 */
public interface Conversation {

    Response sendMessage(String... messages);

    Response sendMessage(ChatGPTRequest request);

    AsyncPlaceholder<Response> sendMessageAsync(String... messages);

    AsyncPlaceholder<Response> sendMessageAsync(ChatGPTRequest request);

    Optional<Response> getFullResponse();

}
