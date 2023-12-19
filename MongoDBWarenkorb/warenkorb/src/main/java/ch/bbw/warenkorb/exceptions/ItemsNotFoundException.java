package ch.bbw.warenkorb.exceptions;

public class ItemsNotFoundException extends RuntimeException {
    public ItemsNotFoundException(String message) {
        super(message);
    }
}