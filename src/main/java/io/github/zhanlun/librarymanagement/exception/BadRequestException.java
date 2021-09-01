package io.github.zhanlun.librarymanagement.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }

    public static BadRequestException createWith(String message) {
        return new BadRequestException(message);
    }
}
