package hu.elte.CataflixBackEnd.payload.response;

public class MessageResponse {
    private String message;

    /**
     * Constructor of MessageResponse using the parameter
     * @param message
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set message using
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
