package gg.acai.chatgpt;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 10.12.2022 19:44
 */
public abstract class StreamResponseListener {

    public void onResponse(StreamResponse response) {}

    public void onFinish(StreamResponse finalResponse) {}

    public void onClose() {}

    public void onFailure(Throwable throwable, boolean ignored) {}

}
