package gg.acai.chatgpt.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Kaiser
 * @since 10/12/2022 16:57
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEntity {
    private String error;
    private String conversation_id;
    private ResponseMessageEntity message;

    public ResponseMessageEntity getMessage() {
        return message;
    }
}
