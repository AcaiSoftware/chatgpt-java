package gg.acai.chatgpt.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Kaiser
 * @since 10/12/2022 16:58
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMessageEntity {
    private String id;
    private String recipient;
    private String end_turn;
    private float weight;
    private String role;
    private UserEntity user;
    private String update_time;
    private String create_time;
    private String[] metadata;
    private ContentEntity content;

    /**
     * Gets the content of this response.
     *
     * @return The content of this response
     */
    public ContentEntity getContent() {
        return content;
    }
}
