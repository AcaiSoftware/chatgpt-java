package gg.acai.chatgpt.exception;

import gg.acai.acava.cache.CacheDuplex;
import gg.acai.acava.cache.CacheLiteral;

import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 10.12.2022 20:32
 */
public class ExceptionParser {

    private final CacheDuplex<String, Class<? extends Exception>> cache;

    public ExceptionParser() {
        this.cache = new CacheLiteral<>();
        registerDefaults();
    }

    public void register(ParsedExceptionEntry entry) {
        this.cache.set(entry.exceptionKeywords(), entry.exceptionClass());
    }

    public void register(List<ParsedExceptionEntry> entries) {
        entries.forEach(this::register);
    }

    public void read(String exceptionMessage) {
        Class<? extends Exception> exceptionClass = this.cache.get(exceptionMessage);
        if (exceptionClass == null) {
            return;
        }

        try {
            throw exceptionClass.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerDefaults() {
        this.register(new ParsedExceptionEntry(
                "{\"detail\":{\"message\":\"Your authentication token has expired. Please try signing in again.\",\"type\":\"invalid_request_error\",\"param\":null,\"code\":\"token_expired\"}}",
                TokenExpiredException.class
        ));
    }

}
