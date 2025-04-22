package com.thnkscj.braindead.transform;

import com.thnkscj.braindead.instruction.BraindeadInstruction;
import com.thnkscj.braindead.instruction.BraindeadInstructionFactory;
import com.thnkscj.braindead.ir.IROp;
import com.thnkscj.braindead.program.BrainfuckInstruction;

import java.util.ArrayList;
import java.util.List;

public class IRToInstructionTransformer {

    private final BraindeadInstructionFactory factory;

    public IRToInstructionTransformer(BraindeadInstructionFactory factory) {
        this.factory = factory;
    }

    public List<BraindeadInstruction> transform(List<IROp> ir) {
        List<BraindeadInstruction> instructions = new ArrayList<>();

        for (IROp op : ir) {
            switch (op.op) {
                case "INC_PTR":
                    instructions.add(factory.createInstruction(BrainfuckInstruction.RIGHT.getSymbol()));
                    break;
                case "DEC_PTR":
                    instructions.add(factory.createInstruction(BrainfuckInstruction.LEFT.getSymbol()));
                    break;
                case "INC_VAL":
                    instructions.add(factory.createInstruction(BrainfuckInstruction.PLUS.getSymbol()));
                    break;
                case "DEC_VAL":
                    instructions.add(factory.createInstruction(BrainfuckInstruction.MINUS.getSymbol()));
                    break;
                case "OUTPUT":
                    instructions.add(factory.createInstruction(BrainfuckInstruction.OUTPUT.getSymbol()));
                    break;
                case "INPUT":
                    instructions.add(factory.createInstruction(BrainfuckInstruction.INPUT.getSymbol()));
                    break;
                case "LOOP_START":
                    instructions.add(factory.createInstruction(BrainfuckInstruction.LOOP_START.getSymbol()));
                    break;
                case "LOOP_END":
                    instructions.add(factory.createInstruction(BrainfuckInstruction.LOOP_END.getSymbol()));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown IR operation: " + op.op);
            }
        }

        return instructions;
    }
}

