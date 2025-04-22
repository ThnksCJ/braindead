package com.thnkscj.braindead.instruction;

import com.thnkscj.braindead.exception.InstructionCreationException;
import com.thnkscj.braindead.exception.InvalidBrainfuckInstructionException;
import com.thnkscj.braindead.exception.UnmatchedLoopEndException;
import com.thnkscj.braindead.instruction.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BrainfuckInstructionFactory {

    private static final Map<Character, Class<? extends BrainfuckInstruction>> instructionMap = new HashMap<>();
    private static final Stack<LoopStart> loopStartStack = new Stack<>();

    static {
        instructionMap.put('>', IncrementPointer.class);
        instructionMap.put('<', DecrementPointer.class);
        instructionMap.put('+', IncrementValue.class);
        instructionMap.put('-', DecrementValue.class);
        instructionMap.put('.', OutputValue.class);
        instructionMap.put(',', InputValue.class);
        instructionMap.put('[', LoopStart.class);
        instructionMap.put(']', LoopEnd.class);
    }

    public static BrainfuckInstruction getInstruction(char symbol) {
        Class<? extends BrainfuckInstruction> instructionClass = instructionMap.get(symbol);
        if (instructionClass != null) {
            try {
                if (instructionClass == LoopStart.class) {
                    LoopStart loopStart = new LoopStart();
                    loopStartStack.push(loopStart);
                    return loopStart;
                } else if (instructionClass == LoopEnd.class) {
                    if (loopStartStack.isEmpty()) {
                        throw new UnmatchedLoopEndException("Unmatched ']' found.");
                    }
                    LoopStart loopStart = loopStartStack.pop();
                    return new LoopEnd(loopStart);
                } else {
                    return instructionClass.getDeclaredConstructor().newInstance();
                }
            } catch (Exception e) {
                throw new InstructionCreationException("Failed to create instruction instance", e);
            }
        }
        throw new InvalidBrainfuckInstructionException("Invalid Brainfuck instruction: " + symbol);
    }
}
