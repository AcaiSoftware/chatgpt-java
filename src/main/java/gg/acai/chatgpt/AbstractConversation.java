package gg.acai.chatgpt;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.entities.ResponseEntity;
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
import java.util.concurrent.TimeUnit;

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
        ObjectMapper mapper = new ObjectMapper();

        RequestBody b = null;
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

        EventSourceListener listener = new EventSourceListener() {
            @Override
            public void onOpen(EventSource eventSource, okhttp3.Response response) {
                System.out.println("Open");
            }

            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                //System.out.println(eventSource + " " + id + " " + type + " " + data);
                try {
                    ResponseEntity eventResp = mapper.readValue(data, ResponseEntity.class);
                    System.out.println(eventResp.getMessage().getContent().getParts());
                } catch (JsonProcessingException e) {
                    if (data.contains("[DONE]")) {
                        eventSource.cancel();
                        System.out.println("Done :D :D");
                    }
                }
            }

            @Override
            public void onClosed(EventSource eventSource) {
                System.out.println("Closed");
            }

            @Override
            public void onFailure(EventSource eventSource, Throwable t, okhttp3.Response response) {
                if (t instanceof SocketException)
                    return;
                throw new RuntimeException(t);
            }
        };

        final EventSource eventSource = EventSources.createFactory(client).newEventSource(req, listener);


        String s = null;
        okhttp3.Response res = null;
        try {
            res = client.newCall(req).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ResponseBody body = res.body();
        try {
            return () -> {
                try {
                    return body.string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    @Override
    public Optional<Response> getFullResponse() {
        return Optional.empty();
    }
}
