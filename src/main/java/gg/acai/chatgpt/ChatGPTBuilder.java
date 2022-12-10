package gg.acai.chatgpt;

import gg.acai.acava.event.EventBus;
import gg.acai.chatgpt.exception.ParsedExceptionEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:20
 */
public class ChatGPTBuilder {

    private final List<ParsedExceptionEntry> exceptionAttributes = new ArrayList<>();
    private String sessionToken;
    private EventBus eventBus;

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
     * Builds the ChatGPT API
     * @return Returns the ChatGPT API
     */
    public ChatGPT build() {
        doBuildProcedure();
        ChatGPTAPI api = new ChatGPTAPI(this.sessionToken, this.eventBus, this.exceptionAttributes);
        api.getComplexAccessCache().refreshAccessToken();
        return api;
    }

    private void doBuildProcedure() {
        // empty
    }
}
