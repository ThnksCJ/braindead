package com.thnkscj.braindead.instruction;

public class BraindeadInstructionFactory {
    private final InstructionRegistry registry;
    private final LoopContext loopContext;

    public BraindeadInstructionFactory() {
        this.registry = new InstructionRegistry();
        this.loopContext = new LoopContext();
    }

    public BraindeadInstruction createInstruction(char symbol) {
        if (symbol == '[') {
            return loopContext.registerLoopStart();
        } else if (symbol == ']') {
            return loopContext.resolveLoopEnd();
        } else {
            return registry.getInstruction(symbol);
        }
    }
}

