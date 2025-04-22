package com.thnkscj.braindead.instruction;

import com.thnkscj.braindead.exception.InvalidBrainfuckInstructionException;
import com.thnkscj.braindead.instruction.impl.*;
import com.thnkscj.braindead.program.BrainfuckInstruction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class InstructionRegistry {
    private final Map<Character, Supplier<BraindeadInstruction>> instructionMap = new HashMap<>();

    public InstructionRegistry() {
        instructionMap.put(BrainfuckInstruction.RIGHT.getSymbol(), IncrementPointer::new);
        instructionMap.put(BrainfuckInstruction.LEFT.getSymbol(), DecrementPointer::new);
        instructionMap.put(BrainfuckInstruction.PLUS.getSymbol(), IncrementValue::new);
        instructionMap.put(BrainfuckInstruction.MINUS.getSymbol(), DecrementValue::new);
        instructionMap.put(BrainfuckInstruction.OUTPUT.getSymbol(), OutputValue::new);
        instructionMap.put(BrainfuckInstruction.INPUT.getSymbol(), InputValue::new);
    }

    public BraindeadInstruction getInstruction(char symbol) {
        Supplier<BraindeadInstruction> supplier = instructionMap.get(symbol);
        if (supplier == null) {
            throw new InvalidBrainfuckInstructionException("Invalid Brainfuck instruction: " + symbol);
        }
        return supplier.get();
    }
}
