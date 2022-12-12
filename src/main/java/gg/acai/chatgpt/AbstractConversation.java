package gg.acai.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.exception.ExceptionParser;
import gg.acai.chatgpt.request.ChatGPTRequest;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * A conversation extension which retrieves the response.
 * 
 * @author Clouke
 * @since 09.12.2022 18:04
 */
public class AbstractConversation implements Conversation {

    private static final MediaType JSON = MediaType.get("application/json");

    private final UUID uuid;
    private final OkHttpClient client;
    private final ExceptionParser parser;
    private String conversationId;

    public AbstractConversation(OkHttpClient client, UUID uuid, ExceptionParser parser) {
        this.client = client;
        this.uuid = uuid;
        this.parser = parser;
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
        request.setConversationId(conversationId);
        ObjectMapper mapper = new ObjectMapper();

        RequestBody b;
        try {
            b = RequestBody.create(mapper.writeValueAsString(request), JSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ChatGPT gpt = ChatGPTAPI.getInstance();
        Request req = new okhttp3.Request.Builder()
                .header("Authorization", "Bearer " + gpt.getAccessToken())
                .header("Accept", "text/event-stream")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .header("Cookie", "cf_clearance=" + gpt.getCfClearance())
                .url(APIUrls.CONVERSATION_URL.getUrl())
                .post(b)
                .build();

        okhttp3.Response res;
        try {
            res = client.newCall(req).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResponseBody body = res.body();
        return () -> {
            try {
                Objects.requireNonNull(body, "Response body is null");
                String string = body.string();
                this.parser.read(string);
                return string;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
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
    public Conversation setConversationId(String id) {
        this.conversationId = id;
        return this;
    }

}
