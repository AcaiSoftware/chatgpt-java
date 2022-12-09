package gg.acai.chatgpt;

import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:52
 */
public interface Content {

    String getContentType();

    List<String> getParts();

}
