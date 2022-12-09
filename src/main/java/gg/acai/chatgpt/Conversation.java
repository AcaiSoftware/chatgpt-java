package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:58
 */
@FunctionalInterface
public interface Conversation {

    AsyncPlaceholder<Response> sendMessage(String message);

}
