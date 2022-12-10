package gg.acai.chatgpt.event;

import gg.acai.acava.event.Event;
import gg.acai.chatgpt.Response;

/**
 * 
 *
 * @author Clouke
 * @since 09.12.2022 22:13
 */
@Deprecated
public record MessageReceiveEvent(Response response) implements Event {}
