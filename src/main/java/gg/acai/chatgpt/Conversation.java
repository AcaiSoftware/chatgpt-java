package gg.acai.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.chatgpt.request.ChatGPTRequest;

import java.net.URISyntaxException;

/**
 * Interface for a conversation with the ChatGPT API.
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

}
