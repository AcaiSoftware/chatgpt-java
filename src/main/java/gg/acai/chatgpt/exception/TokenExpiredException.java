package gg.acai.chatgpt.exception;

/**
 * Thrown when the session token is expired.
 *
 * @author Clouke
 * @since 10.12.2022 20:43
 */
public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() {
        super("The token has expired");
    }

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExpiredException(Throwable cause) {
        super(cause);
    }

    public TokenExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "TokenExpiredException{" +
                "message=" + getMessage() +
                '}';
    }
}
