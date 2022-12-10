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
 * Request object for the ChatGPT API.
 * 
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

    /**
     * Creates a new RequestBuilder.
     *
     * @return Returns a new RequestBuilder.
     */
    public static RequestBuilder newBuilder() {
        return new RequestBuilder();
    }

    public ChatGPTRequest(RequestBuilder builder) {
        this.action = builder.action;
        this.messages = builder.messages;
        this.parent_message_id = builder.parent_message_id;
        this.model = builder.model;
    }

    /**
     * Gets the action of this request.
     *
     * @return The action of this request
     */
    public String getAction() {
        return action;
    }

    /**
     * Gets the list of messages in this request.
     *
     * @return The list of messages in this request
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Gets the ID of the parent message of this request.
     *
     * @return The ID of the parent message of this request
     */
    public String getParentMessageId() {
        return parent_message_id;
    }

    /**
     * Gets the model used to generate this request.
     *
     * @return The model used to generate this request
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the conversation ID for this request.
     *
     * @param uuid The UUID to set as the conversation ID
     */
    public void setConversationId(UUID uuid) {
        this.conversation_id = uuid.toString();
    }


    /**
     * Builder that creates a new {@link ChatGPTRequest} instance.
     */
    public static class RequestBuilder {

        private String action = "next";
        private MessageEntity message = new MessageEntity();
        private List<Message> messages;
        private String parent_message_id = UUID.randomUUID().toString();
        private String model = "text-davinci-002-render";

        /**
         * Sets the action for this request.
         *
         * @param action The action to set
         * @return This object, for chaining
         */
        public RequestBuilder setAction(String action) {
            this.action = action;
            return this;
        }

        /**
         * Sets the list of messages for this request.
         *
         * @param messages The list of messages to set
         * @return This object, for chaining
         */
        public RequestBuilder setMessages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        /**
         * Sets the content type of the message for this request.
         *
         * @param contentType The content type to set
         * @return This object, for chaining
         */
        public RequestBuilder setContentType(String contentType) {
            this.message.setContentType(contentType);
            return this;
        }

        /**
         * Sets the parts of the message for this request.
         *
         * @param parts The parts to set
         * @return This object, for chaining
         */
        public RequestBuilder setParts(String... parts) {
            this.message.setParts(Arrays.asList(parts));
            return this;
        }

        /**
         * Sets the ID of the message for this request.
         *
         * @param id The ID to set
         * @return This object, for chaining
         */
        public RequestBuilder setId(String id) {
            this.message.setId(id);
            return this;
        }

        /**
         * Sets the role of the message for this request.
         *
         * @param role The role to set
         * @return This object, for chaining
         */
        public RequestBuilder setRole(String role) {
            this.message.setRole(role);
            return this;
        }

        /**
         * Adds one or more messages to the list of messages for this request.
         *
         * @param messages The messages to add
         * @return This object, for chaining
         */
        public RequestBuilder setMessages(Message... messages) {
            if (this.messages == null) this.messages = new ArrayList<>();
            this.messages.addAll(Arrays.asList(messages));
            return this;
        }

        /**
         * Sets the ID of the parent message of this request.
         *
         * @param parent_message_id The ID of the parent message to set
         * @return This object, for chaining
         */
        public RequestBuilder setParentMessageId(String parent_message_id) {
            this.parent_message_id = parent_message_id;
            return this;
        }

        /**
         * Sets the ID of the parent message of this request using a UUID object.
         *
         * @param uuid The UUID to set as the parent message ID
         * @return This object, for chaining
         */
        public RequestBuilder setParentMessageId(UUID uuid) {
            this.parent_message_id = uuid.toString();
            return this;
        }

        /**
         * Sets the model to use for this request.
         *
         * @param model The model to set
         * @return This object, for chaining
         */
        public RequestBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        /**
         * Builds the request object.
         * If the list of messages is empty or null, the message set using the setters will be used.
         *
         * @return The request object
         */
        public ChatGPTRequest build() {
            if (this.messages == null || this.messages.isEmpty()) {
                this.messages = new ArrayList<>();
                this.messages.add(this.message);
            }
            return new ChatGPTRequest(this);
        }

    }
}