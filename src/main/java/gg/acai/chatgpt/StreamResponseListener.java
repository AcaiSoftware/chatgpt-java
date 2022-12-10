package gg.acai.chatgpt;

/**
 * StreamResponseListener is an abstract class used to listen for responses from an Event Stream.
 * The class provides four methods which can be implemented to provide custom responses to different events.
 *
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 10.12.2022 19:44
 */
public abstract class StreamResponseListener {

    /**
     * Called when a response is received from the stream
     *
     * @param response The {@link StreamResponse} containing the message
     */
    public void onResponse(StreamResponse response) {}

    /**
     * Called when the stream is finished
     *
     * @param finalResponse The last response received from the stream
     */
    public void onFinish(StreamResponse finalResponse) {}

    /**
     * Called when the stream is closed
     */
    public void onClose() {}

    /**
     * Called when an error occurs
     *
     * @param throwable The {@link Throwable} that was thrown
     * @param ignored If the error was ignored
     */
    public void onFailure(Throwable throwable, boolean ignored) {}

}
