package gg.acai.chatgpt;

import gg.acai.acava.Requisites;
import gg.acai.chatgpt.types.ChatGPTAPI;
import kong.unirest.Config;
import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:20
 */
public class ChatGPTBuilder {

    private String sessionToken;
    private Config config;

    public ChatGPTBuilder config(Config config) {
        this.config = config;
        return this;
    }

    public ChatGPTBuilder sessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
        return this;
    }

    public ChatGPT build() {
        doBuildProcedure();
        return new ChatGPTAPI(sessionToken, config);
    }

    private void doBuildProcedure() {
        Requisites.requireNonNull(sessionToken, "Session token cannot be null!");
        this.config = Requisites.applyIfNull(this.config, Unirest.config())
                .setObjectMapper(new JacksonObjectMapper() {
                    @Override
                    public <T> T readValue(String value, Class<T> valueType) {
                        return super.readValue(value, valueType);
                    }
                })
                .setDefaultHeader("Content-Type", "application/json")
                .setDefaultHeader("Accept", "application/json")
                .setDefaultHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36");
    }
}
