package k17.example.readingbook.exception;

import k17.example.readingbook.constant.MessageError;

public class InValidEmailException extends RuntimeException{
    public InValidEmailException() {
        super(MessageError.INVALID_EMAIL);
    }
}
