package gg.acai.chatgpt;

import gg.acai.acava.event.EventBus;
import gg.acai.chatgpt.exception.ExceptionParser;
import gg.acai.chatgpt.exception.ParsedExceptionEntry;
import okhttp3.OkHttpClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Main implementation of the ChatGPT interface.
 * 
 * @author Clouke
 * @since 09.12.2022 17:47
 */
public final class ChatGPTAPI implements ChatGPT {

    private static ChatGPTAPI instance;
    private final String sessionToken;
    private final EventBus eventBus;
    private final ComplexAccessCache accessTokenCache;
    private final OkHttpClient client;
    private final ExceptionParser exceptionParser;

    public ChatGPTAPI(String sessionToken, EventBus eventBus, List<ParsedExceptionEntry> entries, long connectTimeout, long readTimeout, long writeTimeout) {
        instance = this;

        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();

        this.sessionToken = sessionToken;
        this.eventBus = eventBus;
        this.accessTokenCache = new ComplexAccessCache(this);
        this.exceptionParser = new ExceptionParser();
        this.exceptionParser.register(entries);
    }

    @Override
    public Conversation createConversation() {
        return new AbstractConversation(this.client, UUID.randomUUID(), this.exceptionParser);
    }

    @Override
    public Conversation createStreamConversation(StreamResponseListener listener) {
        return new AbstractStreamConversation(this.client, UUID.randomUUID(), listener);
    }

    @Override
    public String getSessionToken() {
        return this.sessionToken;
    }

    @Override
    public String getAccessToken() {
        return this.accessTokenCache.get("accessToken");
    }

    @Override
    public Optional<EventBus> getEventBus() {
        return Optional.ofNullable(this.eventBus);
    }

    @Override
    public ComplexAccessCache getComplexAccessCache() {
        return this.accessTokenCache;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return this.client;
    }

    @Override
    public ExceptionParser getExceptionParser() {
        return this.exceptionParser;
    }

    public static ChatGPTAPI getInstance() {
        return instance;
    }
}
