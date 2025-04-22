package com.thnkscj.braindead.instruction.impl;

import com.thnkscj.braindead.instruction.BraindeadInstruction;
import org.objectweb.asm.MethodVisitor;

public class DecrementPointer implements BraindeadInstruction {
    @Override
    public void execute(MethodVisitor mv) {
        mv.visitIincInsn(2, -1);
    }
}
