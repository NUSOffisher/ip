package Tigger;

public class TiggerException extends IllegalArgumentException{
    String message;

    TiggerException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }
}
