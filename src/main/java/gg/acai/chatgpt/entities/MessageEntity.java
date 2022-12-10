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

    public MessageEntity setId(String id) {
        this.id = id;
        return this;
    }

    public MessageEntity setId(UUID uuid) {
        this.id = uuid.toString();
        return this;
    }

    public MessageEntity setRole(String role) {
        this.role = role;
        return this;
    }

    public MessageEntity setContent(Content content) {
        this.content = content;
        return this;
    }

    public MessageEntity setContentType(String content_type) {
        this.content.setContentType(content_type);
        return this;
    }

    public MessageEntity setParts(List<String> parts) {
        this.content.setParts(parts);
        return this;
    }

    @Required
    public MessageEntity setParts(String... parts) {
        this.content.setParts(parts);
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public Content getContent() {
        return content;
    }

}