package gg.acai.chatgpt;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 10.12.2022 19:44
 */
public interface StreamResponseListener {

    void onResponse(StreamResponse response);

    void onFinish();

    void onClose();

    void onFailure(Throwable throwable, boolean ignored);

}
