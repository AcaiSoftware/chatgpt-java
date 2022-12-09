package gg.acai.chatgpt;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 17:52
 */
public interface Message {

    String getId();

    String getRole();

    Content getContent();

}
