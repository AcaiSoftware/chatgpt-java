package gg.acai.chatgpt;

import gg.acai.acava.annotated.Use;
import gg.acai.acava.event.EventBus;
import gg.acai.chatgpt.exception.ExceptionParser;
import okhttp3.OkHttpClient;

import java.util.Optional;

/**
 * Main class for the ChatGPT API.
 *
 * @author Clouke
 * @since 09.12.2022 18:27
 */
@Use("Use ChatGPT#newBuilder to create a ChatGPT instance")
public interface ChatGPT {

    /**
     * Creates a new builder extension for the ChatGPT interface
     *
     * @return Returns a new {@link ChatGPTBuilder}
     */
    static ChatGPTBuilder newBuilder() {
        return new ChatGPTBuilder();
    }

    /**
     * Creates a new conversation
     *
     * @return Returns a new {@link AbstractConversation}
     */
    Conversation createConversation();

    /**
     * Creates a new Event Stream conversation
     *
     * @param listener The listener for the event stream
     * @return Returns a new {@link AbstractStreamConversation}
     */
    Conversation createStreamConversation(StreamResponseListener listener);

    /**
     * Returns the session token
     *
     * @return Returns the session token
     */
    String getSessionToken();

    /**
     * Gets the Cloudflare clearance token
     *
     * @return Returns the Cloudflare clearance token
     */
    String getCfClearance();

    /**
     * Returns the access token
     *
     * @return Returns the access token
     */
    String getAccessToken();

    /**
     * Gets the event bus optionally
     *
     * @return Returns an optional of the event bus, if not registered, it will be empty
     */
    Optional<EventBus> getEventBus();

    /**
     * Gets the access token cache
     *
     * @return Returns the access token cache
     */
    ComplexAccessCache getComplexAccessCache();

    /**
     * Gets the HTTP client
     *
     * @return Returns the HTTP client
     */
    OkHttpClient getHttpClient();

    /**
     * Gets the exception parser
     *
     * @return Returns the exception parser
     */
    ExceptionParser getExceptionParser();

}
