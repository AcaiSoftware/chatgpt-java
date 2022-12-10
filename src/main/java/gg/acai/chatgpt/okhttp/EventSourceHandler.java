package gg.acai.chatgpt.okhttp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gg.acai.chatgpt.StreamResponse;
import gg.acai.chatgpt.StreamResponseListener;
import gg.acai.chatgpt.entities.ResponseEntity;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;

import java.net.SocketException;

/**
 * Handles listening to event source and calls events to its listener and returns its response.
 *
 * @author Kaiser
 * @author Clouke
 * @since 10.12.2022 19:51
 */
public class EventSourceHandler extends EventSourceListener {

    private StreamResponseListener listener;
    private StreamResponse lastResponse;

    public EventSourceHandler(StreamResponseListener listener) {
        this.listener = listener;
    }

    public EventSourceHandler() {
    }

    public void setListener(StreamResponseListener listener) {
        this.listener = listener;
    }

    @Override
    public void onEvent(@NotNull EventSource eventSource, String id, String type, @NotNull String data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ResponseEntity eventResp = mapper.readValue(data, ResponseEntity.class);

            StringBuilder buffer = new StringBuilder();
            eventResp.getMessage()
                    .getContent()
                    .getParts()
                    .forEach(buffer::append);

            StreamResponse resp = buffer::toString;
            if (listener != null) {
                listener.onResponse(resp);
            }
            lastResponse = resp;
        } catch (JsonProcessingException e) {
            if (data.contains("[DONE]")) {
                eventSource.cancel();
                if (listener != null)
                    listener.onFinish(lastResponse);
            }
        }
    }

    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        if (listener != null)
            listener.onClose();
    }

    @Override
    public void onFailure(@NotNull EventSource eventSource, Throwable t, okhttp3.Response response) {
        boolean ignored = t instanceof SocketException;
        if (listener != null)
            listener.onFailure(t, ignored);
    }
}
