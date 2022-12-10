package gg.acai.chatgpt;

import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:52
 */
public interface Content {

    /**
     * Gets the content type
     *
     * @return Returns the content type
     */
    String getContentType();

    /**
     * Gets the content parts
     *
     * @return Returns a list of content parts
     */
    List<String> getParts();

    /**
     * Sets the content type
     *
     * @param content_type The content type to set
     * @return Returns the content instance
     */
    Content setContentType(String content_type);

    /**
     * Sets the content parts
     *
     * @param parts The content parts to set
     * @return Returns the content instance
     */
    Content setParts(List<String> parts);

    /**
     * Adds the content parts
     *
     * @param parts The content parts to add
     * @return Returns the content instance
     */
    Content setParts(String... parts);


}
