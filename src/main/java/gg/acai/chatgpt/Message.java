package gg.acai.chatgpt;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:52
 */
public interface Message {

    /**
     * Gets the Identifier of the message
     *
     * @return Returns the identifier
     */
    String getId();

    /**
     * Gets the role of the Message
     *
     * @return Returns the role
     */
    String getRole();

    /**
     * Gets the content of the message
     *
     * @return Returns the content
     */
    Content getContent();

}
