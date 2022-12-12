package gg.acai.chatgpt.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity for the response object.
 *
 * @author Kaiser
 * @since 10/12/2022 16:57
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEntity {
    private String error;
    private String conversation_id;
    private ResponseMessageEntity message;

    /**
     * Gets the message of this response.
     *
     * @return The message of this response
     */
    public ResponseMessageEntity getMessage() {
        return message;
    }

    /**
     * Gets the conversation id of this response.
     *
     * @return The conversation id of this response
     */
    public String getConversationId() {
        return conversation_id;
    }

}
