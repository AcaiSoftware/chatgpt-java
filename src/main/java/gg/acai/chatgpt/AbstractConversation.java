package gg.acai.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.okhttp.EventSourceHandler;
import gg.acai.chatgpt.request.ChatGPTRequest;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSources;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:04
 */
public class AbstractConversation implements Conversation {

    private static final MediaType JSON = MediaType.get("application/json");
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    private final UUID uuid;
    private final EventSourceHandler handler;

    public AbstractConversation(UUID uuid) {
        this.uuid = uuid;
        this.handler = new EventSourceHandler();
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
        ObjectMapper mapper = new ObjectMapper();

        RequestBody b;
        try {
            b = RequestBody.create(mapper.writeValueAsString(request), JSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Request req = new okhttp3.Request.Builder()
                .header("Authorization", "Bearer " + ChatGPTAPI.getInstance().getAccessToken())
                .header("Accept", "text/event-stream")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36")
                .url(APIUrls.CONVERSATION_URL.getUrl())
                .post(b)
                .build();

        EventSources.createFactory(client).newEventSource(req, handler);
        okhttp3.Response res;
        try {
            res = client.newCall(req).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResponseBody body = res.body();
        try {
            return () -> {
                try {
                    return Objects.requireNonNull(body, "Body is null").string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
        } finally {
            res.close();
        }
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
    public Conversation setStreamResponseListener(StreamResponseListener streamResponseListener) {
        this.handler.setListener(streamResponseListener);
        return this;
    }

}
