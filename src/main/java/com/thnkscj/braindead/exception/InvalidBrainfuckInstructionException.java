package com.thnkscj.braindead.exception;

public class InvalidBrainfuckInstructionException extends IllegalArgumentException {
    public InvalidBrainfuckInstructionException(String message) {
        super(message);
    }
}
