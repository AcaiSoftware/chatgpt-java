package gg.acai.chatgpt.types;

import gg.acai.acava.cache.CacheDuplex;
import gg.acai.acava.cache.CacheExpire;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.APIUrls;
import gg.acai.chatgpt.AbstractConversation;
import gg.acai.chatgpt.ChatGPT;
import gg.acai.chatgpt.Conversation;
import kong.unirest.Config;
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

    public AsyncPlaceholder<String> refreshAccessToken() {
        String cachedAccessToken = this.accessTokenCache.get("accessToken");
        if (cachedAccessToken != null) {
            return Schedulers.supplyAsync(() -> cachedAccessToken);
        }

        AsyncPlaceholder<JsonNode> future = Schedulers.supplyAsync(() -> {
            try {
                return Unirest.post(APIUrls.REFRESH_TOKEN_URL.getUrl())
                        .cookie("__Secure-next-auth.session-token", this.sessionToken)
                        .asJson()
                        .getBody();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        future.whenComplete(res -> {
            String accessToken = "access_token";// res.getAccessToken();
            if (accessToken == null) {
                throw new RuntimeException("Unauthorized");
            }

            String error = ""; //res.getError();
            if (error != null) {
                if (error.equals("RefreshAccessTokenError")) {
                    throw new CompletionException(new Exception("session token may have expired"));
                } else {
                    throw new CompletionException(new Exception(error));
                }
            }

            this.accessTokenCache.set("accessToken", accessToken);
        });
        return null; // return access token here
    }
}
