public class LionException extends Exception {
    public LionException() {
    }

    public LionException(String message) {
        super(message);
    }

    public LionException(String message, Throwable cause) {
        super(message, cause);
    }

    public LionException(Throwable cause) {
        super(cause);
    }

    public LionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
