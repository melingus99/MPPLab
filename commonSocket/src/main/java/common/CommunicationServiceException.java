package common;

public class CommunicationServiceException extends RuntimeException {

    public CommunicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommunicationServiceException(Throwable cause) {
        super(cause);
    }
}
