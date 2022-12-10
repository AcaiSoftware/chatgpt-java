package gg.acai.chatgpt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gg.acai.acava.annotated.Required;
import gg.acai.chatgpt.Content;
import gg.acai.chatgpt.Message;

import java.util.List;
import java.util.UUID;

/**
 * © Acai Software - All Rights Reserved
 * @author Kaiser
 * @since 08.12.2022 18:33
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageEntity implements Message {

    private String id = UUID.randomUUID().toString();
    private String role = "user";
    private Content content = new ContentEntity();

    /**
     * Sets the ID of this message.
     *
     * @param id The ID to set
     * @return This object, for chaining
     */
    public MessageEntity setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the ID of this message using a UUID object.
     *
     * @param uuid The UUID to set as the ID
     * @return This object, for chaining
     */
    public MessageEntity setId(UUID uuid) {
        this.id = uuid.toString();
        return this;
    }

    /**
     * Sets the role of this message.
     *
     * @param role The role to set
     * @return This object, for chaining
     */
    public MessageEntity setRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Sets the content of this message.
     *
     * @param content The content to set
     * @return This object, for chaining
     */
    public MessageEntity setContent(Content content) {
        this.content = content;
        return this;
    }

    /**
     * Sets the content type of this message.
     *
     * @param content_type The content type to set
     * @return This object, for chaining
     */
    public MessageEntity setContentType(String content_type) {
        this.content.setContentType(content_type);
        return this;
    }

    /**
     * Sets the parts of the content of this message.
     *
     * @param parts The list of parts to set
     * @return This object, for chaining
     */
    public MessageEntity setParts(List<String> parts) {
        this.content.setParts(parts);
        return this;
    }

    /**
     * Adds one or more parts to the content of this message.
     *
     * @param parts The parts to add
     * @return This object, for chaining
     */
    @Required
    public MessageEntity setParts(String... parts) {
        this.content.setParts(parts);
        return this;
    }

    /**
     * Gets the ID of this message.
     *
     * @return The ID of this message
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Gets the role of this message.
     *
     * @return The role of this message
     */
    @Override
    public String getRole() {
        return role;
    }

    /**
     * Gets the content of this message.
     *
     * @return The content of this message
     */
    @Override
    public Content getContent() {
        return content;
    }

}