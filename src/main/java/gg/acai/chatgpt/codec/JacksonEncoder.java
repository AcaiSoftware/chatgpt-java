package gg.acai.chatgpt.codec;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import kong.unirest.jackson.JacksonObjectMapper;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Kaiser
 * @since 09/12/2022 20:36
 */
public class JacksonEncoder extends JacksonObjectMapper {

    @Override
    public String writeValue(Object value) {
        return super.writeValue(value);
    }

    @Override
    public <T> T readValue(String value, Class<T> valueType) {
        return super.readValue(value, valueType);
    }
}
