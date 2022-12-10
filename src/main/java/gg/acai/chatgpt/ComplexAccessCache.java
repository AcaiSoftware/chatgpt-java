package gg.acai.chatgpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import gg.acai.acava.cache.CacheDuplex;
import gg.acai.acava.cache.CacheExpire;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.entities.AuthSessionEntity;
import gg.acai.chatgpt.exception.TokenExpiredException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * A simple cache extension for the {@link AuthSessionEntity} class, containing the expiring cached access token.
 *
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 22:05
 */
public class ComplexAccessCache {

    private final CacheDuplex<String, String> cache;
    private final OkHttpClient client;
    private final String sessionToken;

    public ComplexAccessCache(ChatGPT chatgpt) {
        this.cache = new CacheExpire<>(Schedulers.async(), TimeUnit.SECONDS, 60);
        this.client = chatgpt.getHttpClient();
        this.sessionToken = chatgpt.getSessionToken();
    }

    /**
     * Gets the access token from the cache, if it is not expired.
     *
     * @param key The key to get the access token from.
     * @return Returns the access token if it is not expired, otherwise it will return null.
     */
    public String get(String key) {
        return this.cache.get(key);
    }

    /**
     * Refreshes the access token from the cache, if it is expired.
     *
     * @return Returns a {@link AsyncPlaceholder<String>} promise of the access token.
     */
    public AsyncPlaceholder<String> refreshAccessToken() {
        return Schedulers.supplyAsync(() -> {
            String cachedAccessToken = this.cache.get("accessToken");
            if (cachedAccessToken != null) {
                return cachedAccessToken;
            }


            ObjectMapper mapper = new ObjectMapper();
            Request req = new okhttp3.Request.Builder()
                    .header("Cookie", "__Secure-next-auth.session-token=" + this.sessionToken)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36")
                    .url(APIUrls.REFRESH_TOKEN_URL.getUrl())
                    .get()
                    .build();

            Response res;
            try {
                res = client.newCall(req).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            AuthSessionEntity entity;
            try {
                entity = mapper.readValue(Objects.requireNonNull(res.body()).string(), AuthSessionEntity.class);
            } catch (Exception e) {
                throw new TokenExpiredException("The provided session token has expired.", e);
            }

            String accessToken = entity.getAccessToken();
            if (accessToken == null) {
                throw new RuntimeException("Unauthorized");
            }

            this.cache.set("accessToken", accessToken);
            return accessToken;
        });
    }
}
