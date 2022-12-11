package gg.acai.chatgpt;

import gg.acai.acava.io.config.JsonMapConstructor;

/**
 * Converts a Json string to a {@link Response}
 *
 * @author Clouke
 * @since 11.12.2022 01:15
 */
public final class ResponseConverter {

    /**
     * Converts a formatted Json string to a single String response.
     * Searches by the {@param keyword} and returns the value of the keyword.
     *
     * @param keyword The keyword to search for
     * @param json The formatted Json string
     * @return Returns the value of the keyword
     */
    public String convert(String keyword, String json) {
        return (String) new JsonMapConstructor(json)
                .asMap()
                .get(keyword);
    }

}
