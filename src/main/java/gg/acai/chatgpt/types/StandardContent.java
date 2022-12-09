package gg.acai.chatgpt.types;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gg.acai.chatgpt.Content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 * @author Kaiser
 * @since 08.12.2022 18:33
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class StandardContent implements Content {

    private final String content_type;
    private final List<String> parts;

    private StandardContent(ContentBuilder builder) {
        this.content_type = builder.content_type;
        this.parts = builder.parts;
    }

    @Override
    public String getContentType() {
        return content_type;
    }

    @Override
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

        public ContentBuilder setParts(String... parts) {
            if (this.parts == null) this.parts = new ArrayList<>();
            this.parts.addAll(Arrays.asList(parts));
            return this;
        }

        public StandardContent build() {
            return new StandardContent(this);
        }
    }
}
