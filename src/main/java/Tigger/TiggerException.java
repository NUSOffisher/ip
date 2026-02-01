package Tigger;

/**
 * Custom exception class for Tigger chatbot.
 */
public class TiggerException extends IllegalArgumentException{
    String message;

    /**
     * Constructor for TiggerException.
     * @param message error message
     */
    TiggerException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }
}
