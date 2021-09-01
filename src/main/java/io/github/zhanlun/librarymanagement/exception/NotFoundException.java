package io.github.zhanlun.librarymanagement.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException createWith(String message) {
        return new NotFoundException(message);
    }
}
