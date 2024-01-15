package ru.sberbank.edu.exception;

import java.io.IOException;

public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }

    public AppException(IOException e) {
        super(e);
    }
}
