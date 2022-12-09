package gg.acai.chatgpt;

public class Message {
    private final String id;
    private final String role;
    private final Content content;

    Message(MessageBuilder builder) {
        this.id = builder.id;
        this.role = builder.role;
        this.content = builder.content;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

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

        public MessageBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public MessageBuilder setContent(Content content) {
            this.content = content;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}