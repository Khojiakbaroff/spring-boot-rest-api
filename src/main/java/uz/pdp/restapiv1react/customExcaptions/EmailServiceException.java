package uz.pdp.restapiv1react.customExcaptions;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException(String e) {
        super(e);
    }
}
