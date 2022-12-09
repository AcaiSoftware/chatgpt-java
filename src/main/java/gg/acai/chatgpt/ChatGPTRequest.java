package gg.acai.chatgpt;

import java.util.List;

public class ChatGPTRequest implements IChatGPTRequest {

    private final String action;
    private final List<Message> messages;
    private final String parent_message_id;
    private final String model;

    public static RequestBuilder newBuilder() {
        return new RequestBuilder();
    }

    private ChatGPTRequest(RequestBuilder builder) {
        this.action = builder.action;
        this.messages = builder.messages;
        this.parent_message_id = builder.parent_message_id;
        this.model = builder.model;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String getParentMessageId() {
        return parent_message_id;
    }

    @Override
    public String getModel() {
        return model;
    }

    public static class RequestBuilder {
        private String action;
        private List<Message> messages;
        private String parent_message_id;
        private String model;

        public RequestBuilder setAction(String action) {
            this.action = action;
            return this;
        }

        public RequestBuilder setMessages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        public RequestBuilder setParentMessageId(String parent_message_id) {
            this.parent_message_id = parent_message_id;
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