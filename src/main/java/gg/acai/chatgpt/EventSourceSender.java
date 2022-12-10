package gg.acai.chatgpt;

import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Kaiser
 * @since 10/12/2022 16:18
 */
public class EventSourceSender extends EventSourceListener {

        private final EventSourceListener listener;

        public EventSourceSender(EventSourceListener listener) {
            this.listener = listener;
        }

        @Override
        public void onOpen(@NotNull okhttp3.sse.EventSource eventSource, @NotNull okhttp3.Response response) {
            listener.onOpen(eventSource, response);
        }

        @Override
        public void onEvent(@NotNull okhttp3.sse.EventSource eventSource, String id, String type, @NotNull String data) {
            listener.onEvent(eventSource, id, type, data);
        }

        @Override
        public void onClosed(@NotNull okhttp3.sse.EventSource eventSource) {
            listener.onClosed(eventSource);
        }

        @Override
        public void onFailure(@NotNull okhttp3.sse.EventSource eventSource, Throwable t, okhttp3.Response response) {
            listener.onFailure(eventSource, t, response);
        }
}
