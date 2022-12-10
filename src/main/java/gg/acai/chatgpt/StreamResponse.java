package gg.acai.chatgpt;

/**
 * A stream response interface for {@link StreamResponseListener} which is used in {@link Conversation}
 * 
 * @author Clouke
 * @since 10.12.2022 19:44
 */
@FunctionalInterface
public interface StreamResponse {

    /**
     * Gets the message of the response
     *
     * @return Returns the message
     */
    String getMessage();

}
