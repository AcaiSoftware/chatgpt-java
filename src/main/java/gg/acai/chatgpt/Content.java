package gg.acai.chatgpt;

import gg.acai.chatgpt.types.StandardContent;

import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:52
 */
public interface Content {

    static StandardContent.ContentBuilder newBuilder() {
        return new StandardContent.ContentBuilder();
    }

    String getContentType();

    List<String> getParts();

}
