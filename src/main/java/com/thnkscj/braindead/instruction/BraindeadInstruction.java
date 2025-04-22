package com.thnkscj.braindead.instruction;

import org.objectweb.asm.MethodVisitor;

public interface BraindeadInstruction {
    void execute(MethodVisitor mv);
}
