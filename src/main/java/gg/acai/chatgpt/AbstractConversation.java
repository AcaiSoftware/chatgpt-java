package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.request.ChatGPTRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Optional;
import java.util.UUID;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:04
 */
public class AbstractConversation implements Conversation {

    private final UUID uuid;
    private String lastMessage;

    public AbstractConversation(UUID uuid) {
        this.uuid = uuid;

    }

    @Override
    public Response sendMessage(String... messages) {
        ChatGPTRequest request = ChatGPTRequest.newBuilder()
                .setParts(messages)
                .build();

        return this.sendMessage(request);
    }

    @Override
    public Response sendMessage(ChatGPTRequest request) {
        request.setConversationId(uuid);

        HttpResponse<String> httpResponse = Unirest.post(APIUrls.CONVERSATION_URL.getUrl())
                .header("Authorization", "Bearer " + ChatGPTAPI.getInstance().getAccessToken())
                .body(request)
                .asString();

        String body = httpResponse.getBody();
        if (body.contains("DONE")) {

        } else {
            this.lastMessage = body;
        }


        return () -> body;
    }

    @Override
    public AsyncPlaceholder<Response> sendMessageAsync(String... messages) {
        return Schedulers.supplyAsync(() -> this.sendMessage(messages));
    }

    @Override
    public AsyncPlaceholder<Response> sendMessageAsync(ChatGPTRequest request) {
        return Schedulers.supplyAsync(() -> sendMessage(request));
    }

    @Override
    public Optional<Response> getFullResponse() {
        return Optional.empty();
    }
}
