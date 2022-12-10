package gg.acai.chatgpt;

/**
 * A response interface for {@link Conversation}
 *
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 17:58
 */
@FunctionalInterface
public interface Response {

    /**
     * Gets the message of the response
     *
     * @return Returns the message
     */
    String getMessage();

}
