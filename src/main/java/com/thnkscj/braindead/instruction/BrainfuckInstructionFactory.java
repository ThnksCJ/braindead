package com.thnkscj.braindead.instruction;

import com.thnkscj.braindead.exception.InvalidBrainfuckInstructionException;
import com.thnkscj.braindead.exception.UnmatchedLoopEndException;
import com.thnkscj.braindead.instruction.impl.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BrainfuckInstructionFactory {

    private static final Map<Character, Supplier<BrainfuckInstruction>> DEFAULT_INSTRUCTIONS = new HashMap<>();

    static {
        DEFAULT_INSTRUCTIONS.put('>', IncrementPointer::new);
        DEFAULT_INSTRUCTIONS.put('<', DecrementPointer::new);
        DEFAULT_INSTRUCTIONS.put('+', IncrementValue::new);
        DEFAULT_INSTRUCTIONS.put('-', DecrementValue::new);
        DEFAULT_INSTRUCTIONS.put('.', OutputValue::new);
        DEFAULT_INSTRUCTIONS.put(',', InputValue::new);
    }

    private final Deque<LoopStart> loopStartStack = new ArrayDeque<>();

    public BrainfuckInstruction createInstruction(char symbol) {
        switch (symbol) {
            case '[':
                LoopStart loopStart = new LoopStart();
                loopStartStack.push(loopStart);
                return loopStart;
            case ']':
                if (loopStartStack.isEmpty()) {
                    throw new UnmatchedLoopEndException("Unmatched ']' found");
                }
                return new LoopEnd(loopStartStack.pop());
            default:
                Supplier<BrainfuckInstruction> supplier = DEFAULT_INSTRUCTIONS.get(symbol);
                if (supplier == null) {
                    throw new InvalidBrainfuckInstructionException("Invalid Brainfuck instruction: " + symbol);
                }
                return supplier.get();
        }
    }
}
