package gg.acai.chatgpt;

import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09/12/2022 16:35
 */
public interface IChatGPTRequest extends Request {

    String getAction();

    List<Message> getMessages();

    String getParentMessageId();

    String getModel();

}
