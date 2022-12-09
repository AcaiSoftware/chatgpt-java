package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.request.ChatGPTRequest;
import gg.acai.chatgpt.types.ChatGPTAPI;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:04
 */
public class AbstractConversation implements Conversation {

    @Override
    public Response sendMessage(ChatGPTRequest request) {
        HttpResponse<String> httpResponse = Unirest.post(APIUrls.CONVERSATION_URL.getUrl())
                .header("Authorization", "Bearer " + ChatGPTAPI.getInstance().getAccessToken())
                .body(request)
                .asString();

        return httpResponse::getBody;
    }

    @Override
    public AsyncPlaceholder<Response> sendMessageAsync(ChatGPTRequest request) {
        return Schedulers.supplyAsync(() -> sendMessage(request));
    }
}
