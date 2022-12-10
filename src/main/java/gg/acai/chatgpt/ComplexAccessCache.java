package gg.acai.chatgpt;

import gg.acai.acava.cache.CacheDuplex;
import gg.acai.acava.cache.CacheExpire;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.entities.AuthSessionEntity;
import kong.unirest.Unirest;

import java.util.concurrent.TimeUnit;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 22:05
 */
public class ComplexAccessCache {

    private final CacheDuplex<String, String> cache;
    private final String sessionToken;

    public ComplexAccessCache(ChatGPT chatgpt) {
        this.cache = new CacheExpire<>(Schedulers.async(), TimeUnit.SECONDS, 60);
        this.sessionToken = chatgpt.getSessionToken();
    }

    public String get(String key) {
        return this.cache.get(key);
    }

    public AsyncPlaceholder<String> refreshAccessToken() {
        return Schedulers.supplyAsync(() -> {
            String cachedAccessToken = this.cache.get("accessToken");
            if (cachedAccessToken != null) {
                return cachedAccessToken;
            }

            AuthSessionEntity resp = Unirest.get(APIUrls.REFRESH_TOKEN_URL.getUrl())
                    .cookie("__Secure-next-auth.session-token", this.sessionToken)
                    .asObject(AuthSessionEntity.class)
                    .getBody();

            String accessToken = resp.getAccessToken();
            if (accessToken == null) {
                throw new RuntimeException("Unauthorized");
            }

            this.cache.set("accessToken", accessToken);
            return accessToken;
        });
    }
}
