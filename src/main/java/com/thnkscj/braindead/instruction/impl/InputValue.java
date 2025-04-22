package com.thnkscj.braindead.instruction.impl;

import com.thnkscj.braindead.instruction.BrainfuckInstruction;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class InputValue implements BrainfuckInstruction {
    @Override
    public void execute(MethodVisitor mv) {
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ILOAD, 2);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false);
        mv.visitInsn(I2B);
        mv.visitInsn(BASTORE);
    }
}
