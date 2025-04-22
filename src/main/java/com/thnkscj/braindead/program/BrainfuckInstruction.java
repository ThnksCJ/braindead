package com.thnkscj.braindead.program;

public enum BrainfuckInstruction {
    RIGHT('>'),
    LEFT('<'),
    PLUS('+'),
    MINUS('-'),
    OUTPUT('.'),
    INPUT(','),
    LOOP_START('['),
    LOOP_END(']');

    private final char symbol;

    BrainfuckInstruction(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static BrainfuckInstruction getInstructionByChar(char c) {
        for (BrainfuckInstruction instruction : values()) {
            if (instruction.getSymbol() == c) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("Invalid Brainfuck symbol: " + c);
    }
}
