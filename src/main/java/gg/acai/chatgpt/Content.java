package gg.acai.chatgpt;

import java.util.List;

public class Content {
    private final String content_type;
    private final List<String> parts;

    private Content(ContentBuilder builder) {
        this.content_type = builder.content_type;
        this.parts = builder.parts;
    }

    public String getContentType() {
        return content_type;
    }

    public List<String> getParts() {
        return parts;
    }

    public static class ContentBuilder {
        private String content_type;
        private List<String> parts;

        public ContentBuilder setContentType(String content_type) {
            this.content_type = content_type;
            return this;
        }

        public ContentBuilder setParts(List<String> parts) {
            this.parts = parts;
            return this;
        }

        public Content build() {
            return new Content(this);
        }
    }
}
