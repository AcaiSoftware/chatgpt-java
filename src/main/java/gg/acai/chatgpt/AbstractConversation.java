package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.request.ChatGPTRequest;
import gg.acai.chatgpt.types.StandardContent;
import gg.acai.chatgpt.types.StandardMessage;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:04
 */
public class AbstractConversation implements Conversation {

    @Override
    public Response sendMessage(String message) {
        List<String> list = new ArrayList<>();
        list.add(message);
        Message msg = new StandardMessage.MessageBuilder()
                .setContent(new StandardContent.ContentBuilder()
                        .setContentType("text")
                        .setParts(list)
                        .build())
                .build();

        ChatGPTRequest request = ChatGPTRequest.newBuilder()
                .setMessages(Collections.singletonList(msg))
                .setAction("next")
                .setParentMessageId(UUID.randomUUID().toString())
                .setModel("text-davinci-002-render")
                .build();

        HttpResponse<String> httpResponse = Unirest.post(APIUrls.CONVERSATION_URL.getUrl())
                .body(request)
                .asString();

        return httpResponse::getBody;
    }

    @Override
    public AsyncPlaceholder<Response> sendMessageAsync(String message) {
        return Schedulers.supplyAsync(() -> sendMessage(message));
    }
}
