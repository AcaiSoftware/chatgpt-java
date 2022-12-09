package gg.acai.chatgpt.event;

import gg.acai.acava.event.Event;
import gg.acai.chatgpt.Response;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 22:13
 */
public class MessageReceiveEvent implements Event {

    private final Response response;

    public MessageReceiveEvent(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return this.response;
    }

}
