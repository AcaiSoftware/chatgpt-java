package gg.acai.chatgpt.types;

import gg.acai.acava.cache.CacheDuplex;
import gg.acai.acava.cache.CacheExpire;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Scheduler;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.APIUrls;
import gg.acai.chatgpt.AbstractConversation;
import gg.acai.chatgpt.ChatGPT;
import gg.acai.chatgpt.Conversation;
import gg.acai.chatgpt.entities.AuthSessionEntity;
import kong.unirest.Config;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 17:47
 */
public class ChatGPTAPI implements ChatGPT {

    private static ChatGPTAPI instance;
    private final String sessionToken;
    private final Config config;
    private final CacheDuplex<String, String> accessTokenCache;

    public ChatGPTAPI(String sessionToken, Config config) {
        instance = this;

        this.sessionToken = sessionToken;
        this.config = config;
        this.accessTokenCache = new CacheExpire<>(Schedulers.async().createTask(), TimeUnit.SECONDS, 60);
    }

    @Override
    public Conversation createConversation() {
        return new AbstractConversation();
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public static ChatGPTAPI getInstance() {
        return instance;
    }

    @Override
    public AsyncPlaceholder<String> refreshAccessToken() {
        return Schedulers.supplyAsync(() -> {
            String cachedAccessToken = this.accessTokenCache.get("accessToken");
            if (cachedAccessToken != null) {
                return cachedAccessToken;
            }

            AuthSessionEntity resp = Unirest.post(APIUrls.REFRESH_TOKEN_URL.getUrl())
                    .cookie("__Secure-next-auth.session-token", this.sessionToken)
                    .asObject(AuthSessionEntity.class)
                    .getBody();

            String accessToken = resp.getAccessToken();
            if (accessToken == null) {
                throw new RuntimeException("Unauthorized");
            }

            this.accessTokenCache.set("accessToken", accessToken);
            return accessToken;
        });
    }
}
