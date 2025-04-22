package com.thnkscj.braindead.compiler.impl;

import com.thnkscj.braindead.exception.CompilerException;
import com.thnkscj.braindead.instruction.BrainfuckInstruction;
import com.thnkscj.braindead.parser.BrainfuckParser;
import com.thnkscj.braindead.program.BrainfuckProgram;

public final class DefaultBrainfuckCompiler extends AsmBrainfuckCompiler {
    public DefaultBrainfuckCompiler(BrainfuckParser parser) {
        super(parser);
    }

    @Override
    protected void processInstructions() {
        try {
            BrainfuckProgram program = parser.parse();
            for (BrainfuckInstruction instruction : program.instructions()) {
                instruction.execute(methodVisitor);
            }
        } catch (Exception e) {
            throw new CompilerException("Error processing instructions", e);
        }
    }
}
