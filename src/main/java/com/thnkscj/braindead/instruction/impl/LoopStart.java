package com.thnkscj.braindead.instruction.impl;

import com.thnkscj.braindead.instruction.BraindeadInstruction;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class LoopStart implements BraindeadInstruction {
    private final Label startLabel = new Label();
    private final Label endLabel = new Label();

    @Override
    public void execute(MethodVisitor mv) {
        mv.visitLabel(startLabel);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ILOAD, 2);
        mv.visitInsn(BALOAD);
        mv.visitInsn(I2C);
        mv.visitJumpInsn(IFEQ, endLabel);
    }

    public Label getStartLabel() {
        return startLabel;
    }

    public Label getEndLabel() {
        return endLabel;
    }
}
