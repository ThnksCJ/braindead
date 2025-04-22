package com.thnkscj.braindead.instruction.impl;

import com.thnkscj.braindead.instruction.BraindeadInstruction;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.GOTO;

public class LoopEnd implements BraindeadInstruction {
    private final Label startLabel;
    private final Label endLabel;

    public LoopEnd(LoopStart loopStart) {
        this.startLabel = loopStart.getStartLabel();
        this.endLabel = loopStart.getEndLabel();
    }

    @Override
    public void execute(MethodVisitor mv) {
        mv.visitJumpInsn(GOTO, startLabel);
        mv.visitLabel(endLabel);
    }
}
