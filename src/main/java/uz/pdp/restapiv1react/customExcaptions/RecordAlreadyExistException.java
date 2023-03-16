package uz.pdp.restapiv1react.customExcaptions;

public class RecordAlreadyExistException extends RuntimeException {
    public RecordAlreadyExistException(String message) {
        super(message);
    }
}
