package gg.acai.chatgpt;

import gg.acai.chatgpt.entities.MessageEntity;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:52
 */
public interface Message {

    static MessageEntity.MessageBuilder newBuilder() {
        return new MessageEntity.MessageBuilder();
    }

    String getId();

    String getRole();

    Content getContent();

}
