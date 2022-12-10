package gg.acai.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.chatgpt.request.ChatGPTRequest;

import java.net.URISyntaxException;
import java.util.Optional;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:58
 */
public interface Conversation {

    /**
     * Sends a message to the conversation.
     *
     * @param messages The messages to send.
     * @return Returns the response of the ChatGPT
     */
    Response sendMessage(String... messages);

    /**
     * Sends a request to the conversation.
     *
     * @param request The request to send.
     * @throws URISyntaxException If the URI is invalid.
     * @throws JsonProcessingException If the request cannot be parsed.
     * @return Returns the response of the ChatGPT
     */
    Response sendMessage(ChatGPTRequest request) throws URISyntaxException, JsonProcessingException;

    /**
     * Sends a message to the conversation.
     *
     * @param messages The messages to send.
     * @return Returns the response of the ChatGPT
     */
    AsyncPlaceholder<Response> sendMessageAsync(String... messages);

    /**
     * Sends a request to the conversation.
     *
     * @param request The request to send.
     * @throws URISyntaxException If the URI is invalid.
     * @throws JsonProcessingException If the request cannot be parsed.
     * @return Returns the response of the ChatGPT
     */
    AsyncPlaceholder<Response> sendMessageAsync(ChatGPTRequest request) throws URISyntaxException, JsonProcessingException;

    /**
     * Sets the event handler for stream events.
     *
     * @param streamResponseListener The {@link StreamResponseListener} to set.
     * @return Returns the conversation.
     */
    Conversation setStreamResponseListener(StreamResponseListener streamResponseListener);

}
