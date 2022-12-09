package gg.acai.chatgpt.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gg.acai.chatgpt.Message;
import gg.acai.chatgpt.entities.MessageEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * © Acai Software - All Rights Reserved
 * @author Kaiser
 * @since 08.12.2022 18:33
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ChatGPTRequest {

    private final String action;
    private final List<Message> messages;
    private final String parent_message_id;
    private final String model;
    private String conversation_id;

    public static RequestBuilder newBuilder() {
        return new RequestBuilder();
    }

    public ChatGPTRequest(RequestBuilder builder) {
        this.action = builder.action;
        this.messages = builder.messages;
        this.parent_message_id = builder.parent_message_id;
        this.model = builder.model;
    }

    public String getAction() {
        return action;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getParentMessageId() {
        return parent_message_id;
    }

    public String getModel() {
        return model;
    }

    public void setConversationId(UUID uuid) {
        this.conversation_id = uuid.toString();
    }

    public static class RequestBuilder {

        private String action = "next";
        private MessageEntity message = new MessageEntity();
        private List<Message> messages;
        private String parent_message_id = UUID.randomUUID().toString();
        private String model = "text-davinci-002-render";

        public RequestBuilder setAction(String action) {
            this.action = action;
            return this;
        }

        public RequestBuilder setMessages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        public RequestBuilder setContentType(String contentType) {
            this.message.setContentType(contentType);
            return this;
        }

        public RequestBuilder setParts(String... parts) {
            this.message.setParts(Arrays.asList(parts));
            return this;
        }

        public RequestBuilder setId(String id) {
            this.message.setId(id);
            return this;
        }

        public RequestBuilder setRole(String role) {
            this.message.setRole(role);
            return this;
        }

        public RequestBuilder setMessages(Message... messages) {
            if (this.messages == null) this.messages = new ArrayList<>();
            this.messages.addAll(Arrays.asList(messages));
            return this;
        }

        public RequestBuilder setParentMessageId(String parent_message_id) {
            this.parent_message_id = parent_message_id;
            return this;
        }

        public RequestBuilder setParentMessageId(UUID uuid) {
            this.parent_message_id = uuid.toString();
            return this;
        }

        public RequestBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public ChatGPTRequest build() {
            return new ChatGPTRequest(this);
        }
    }
}