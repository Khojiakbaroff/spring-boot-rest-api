package uz.pdp.restapiv1react.customExcaptions;

public class EmailServiceException extends Throwable {
    public EmailServiceException(Exception message) {
        super(message);
    }
}
