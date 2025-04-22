package com.thnkscj.braindead.instruction.impl;

import com.thnkscj.braindead.instruction.BraindeadInstruction;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class IncrementValue implements BraindeadInstruction {
    @Override
    public void execute(MethodVisitor mv) {
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ILOAD, 2);
        mv.visitInsn(DUP2);
        mv.visitInsn(BALOAD);
        mv.visitInsn(ICONST_1);
        mv.visitInsn(IADD);
        mv.visitInsn(I2B);
        mv.visitInsn(BASTORE);
    }
}
