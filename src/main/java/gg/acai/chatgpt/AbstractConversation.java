package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.request.ChatGPTRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import okhttp3.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:04
 */
public class AbstractConversation implements Conversation {

    private static final MediaType JSON = MediaType.get("application/json");
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Type type = new TypeToken<ChatGPTRequest>(){}.getType();
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

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
