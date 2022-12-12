package gg.acai.chatgpt;

import gg.acai.acava.Requisites;
import gg.acai.acava.event.EventBus;
import gg.acai.chatgpt.exception.ParsedExceptionEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder extension for the ChatGPT interface.
 * 
 * @author Clouke
 * @since 09.12.2022 18:20
 */
public class ChatGPTBuilder {

    private final List<ParsedExceptionEntry> exceptionAttributes = new ArrayList<>();
    private String sessionToken;
    private EventBus eventBus;
    private long readTimeout;
    private long connectTimeout;
    private long writeTimeout;
    private String cfClearance;
    private String userAgent;

    /**
     * Specifies the session token of the ChatGPT API
     *
     * @param sessionToken The session token
     * @return Returns the builder instance
     */
    public ChatGPTBuilder sessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
        return this;
    }

    public ChatGPTBuilder cfClearance(String cfClearance) {
        this.cfClearance = cfClearance;
        return this;
    }

    /**
     * <p> Deprecated: Coming soon </p>
     * Sets the event bus handler of the ChatGPT API
     *
     * @param eventBus The event bus
     * @return Returns the builder instance
     */
    @Deprecated
    public ChatGPTBuilder eventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        return this;
    }

    /**
     * Registers a new {@link ParsedExceptionEntry} which handles exceptions by reading the response body
     *
     * @param exceptionAttribute The exception attribute
     * @return Returns the builder instance
     */
    public ChatGPTBuilder addExceptionAttribute(ParsedExceptionEntry exceptionAttribute) {
        this.exceptionAttributes.add(exceptionAttribute);
        return this;
    }

    /**
     * Specifies the limited read timeout of the http client
     *
     * @param readTimeout The read timeout
     * @return Returns the builder instance
     */
    public ChatGPTBuilder readTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    /**
     * Specifies the limited connect timeout of the http client
     *
     * @param connectTimeout The connection timeout
     * @return Returns the builder instance
     */
    public ChatGPTBuilder connectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * Specifies the limited write timeout of the http client
     *
     * @param writeTimeout The write timeout
     * @return Returns the builder instance
     */
    public ChatGPTBuilder writeTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public ChatGPTBuilder userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * Builds the ChatGPT API
     * @return Returns the ChatGPT API
     */
    public ChatGPT build() {
        doBuildProcedure();
        ChatGPTAPI api = new ChatGPTAPI(sessionToken, cfClearance, userAgent, eventBus, exceptionAttributes, connectTimeout, readTimeout, writeTimeout);
        api.getComplexAccessCache().refreshAccessToken();
        return api;
    }

    private void doBuildProcedure() {
        Requisites.requireNonNull(sessionToken, "Session token cannot be null");
        if (userAgent == null) userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
        if (connectTimeout == 0) connectTimeout = 60;
        if (readTimeout == 0) readTimeout = 30;
        if (writeTimeout == 0) writeTimeout = 30;
    }


}
