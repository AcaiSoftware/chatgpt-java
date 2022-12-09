package gg.acai.chatgpt.request;

import gg.acai.chatgpt.Message;

import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09/12/2022 16:35
 */
public interface ChatGPTRequest extends Request {

    String getAction();

    List<Message> getMessages();

    String getParentMessageId();

    String getModel();

}
