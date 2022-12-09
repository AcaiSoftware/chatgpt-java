package gg.acai.chatgpt;

import gg.acai.chatgpt.types.StandardMessage;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:52
 */
public interface Message {

    static StandardMessage.MessageBuilder newBuilder() {
        return new StandardMessage.MessageBuilder();
    }

    String getId();

    String getRole();

    Content getContent();

}
