package gg.acai.chatgpt.entities;

import com.fasterxml.jackson.annotation.JsonSetter;
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
public final class ContentEntity implements Content {

    private String content_type = "text";
    private List<String> parts;

    /**
     * Sets the content type.
     *
     * @param content_type The content type to set
     * @return This object, for chaining
     */
    @Override
    public Content setContentType(String content_type) {
        this.content_type = content_type;
        return this;
    }

    /**
     * Sets the list of parts for this content.
     *
     * @param parts The list of parts to set
     * @return This object, for chaining
     */
    @JsonSetter("parts")
    @Override
    public Content setParts(List<String> parts) {
        this.parts = parts;
        return this;
    }

    /**
     * Adds one or more parts to the list of parts for this content.
     *
     * @param parts The parts to add
     * @return This object, for chaining
     */
    @Override
    public Content setParts(String... parts) {
        if (this.parts == null) this.parts = new ArrayList<>();
        this.parts.addAll(Arrays.asList(parts));
        return this;
    }

    /**
     * Gets the content type.
     *
     * @return The content type
     */
    @Override
    public String getContentType() {
        return content_type;
    }

    /**
     * Gets the list of parts for this content.
     *
     * @return The list of parts
     */
    @Override
    public List<String> getParts() {
        return parts;
    }

}