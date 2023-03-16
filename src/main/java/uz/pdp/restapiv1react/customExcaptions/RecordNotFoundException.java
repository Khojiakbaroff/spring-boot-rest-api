package uz.pdp.restapiv1react.customExcaptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message) {
        super(message);
    }
}
