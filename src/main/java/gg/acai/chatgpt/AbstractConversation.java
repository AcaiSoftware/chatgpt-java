package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:04
 */
public class AbstractConversation implements Conversation {

    @Override
    public Response sendMessage(String message) {
        HttpResponse<String> httpResponse = Unirest.post(APIUrls.CONVERSATION_URL.getUrl())
                .body("{\"message\": \"" + message + "\"}")
                .asString();

        return httpResponse::getBody;
    }

    @Override
    public AsyncPlaceholder<Response> sendMessageAsync(String message) {
        return Schedulers.supplyAsync(() -> sendMessage(message));
    }
}
