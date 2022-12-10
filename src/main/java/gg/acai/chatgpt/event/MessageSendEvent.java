package gg.acai.chatgpt.event;

import gg.acai.acava.event.Cancellable;
import gg.acai.acava.event.Event;
import gg.acai.chatgpt.Message;

/**
 * 
 *
 * @author Clouke
 * @since 09.12.2022 22:14
 */
@Deprecated
public class MessageSendEvent implements Event, Cancellable {

    private final Message message;
    private boolean cancelled;

    public MessageSendEvent(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void cancel() {
        this.cancelled = true;
    }
}
