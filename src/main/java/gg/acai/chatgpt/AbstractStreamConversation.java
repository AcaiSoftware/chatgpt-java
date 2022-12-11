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
import okhttp3.sse.EventSources;

import java.util.UUID;

/**
 A conversation extension which uses the stream endpoint.
 *
 * @author Clouke
 * @since 11.12.2022 03:02
 */
public class AbstractStreamConversation implements Conversation {

    private static final MediaType JSON = MediaType.get("application/json");

    private final UUID uuid;
    private final EventSourceHandler handler;
    private final OkHttpClient client;

    public AbstractStreamConversation(OkHttpClient client, UUID uuid, StreamResponseListener listener) {
        this.client = client;
        this.uuid = uuid;
        this.handler = new EventSourceHandler();
        this.handler.setListener(listener);
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
        return null;
    }

    @Override
    public AsyncPlaceholder<Response> sendMessageAsync(String... messages) {
        return Schedulers.supplyAsync(() -> this.sendMessage(messages));
    }

    @Override
    public AsyncPlaceholder<Response> sendMessageAsync(ChatGPTRequest request) {
        return Schedulers.supplyAsync(() -> sendMessage(request));
    }

}
