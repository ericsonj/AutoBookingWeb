package test.elements;

/**
 *
 * @author ejoseph
 */
public class ResponseMessage {
    
    private String code;
    private boolean isSuccessful;
    private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(String code, boolean isSuccessful, String message) {
        this.code = code;
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponeMessage{" + "code=" + code + ", isSuccessful=" + isSuccessful + ", message=" + message + '}';
    }
 
}
