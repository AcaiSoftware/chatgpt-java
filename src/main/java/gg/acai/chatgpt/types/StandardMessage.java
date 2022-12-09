package gg.acai.chatgpt.types;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gg.acai.chatgpt.Content;
import gg.acai.chatgpt.Message;

import java.util.UUID;

/**
 * © Acai Software - All Rights Reserved
 * @author Kaiser
 * @since 08.12.2022 18:33
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StandardMessage implements Message {

    private final String id;
    private final String role;
    private final Content content;

    private StandardMessage(MessageBuilder builder) {
        this.id = builder.id;
        this.role = builder.role;
        this.content = builder.content;
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

    public static class MessageBuilder {
        private String id;
        private String role;
        private Content content;

        public MessageBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public MessageBuilder setId(UUID uuid) {
            this.id = uuid.toString();
            return this;
        }

        public MessageBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public MessageBuilder setContent(Content content) {
            this.content = content;
            return this;
        }

        public StandardMessage build() {
            return new StandardMessage(this);
        }
    }
}