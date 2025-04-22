package com.thnkscj.braindead.instruction.impl;

import com.thnkscj.braindead.instruction.BrainfuckInstruction;
import org.objectweb.asm.MethodVisitor;

public class DecrementPointer implements BrainfuckInstruction {
    @Override
    public void execute(MethodVisitor mv) {
        mv.visitIincInsn(2, -1);
    }
}
