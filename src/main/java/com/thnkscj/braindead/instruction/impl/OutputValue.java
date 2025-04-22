package com.thnkscj.braindead.instruction.impl;

import com.thnkscj.braindead.instruction.BrainfuckInstruction;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class OutputValue implements BrainfuckInstruction {
    @Override
    public void execute(MethodVisitor mv) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ILOAD, 2);
        mv.visitInsn(BALOAD);
        mv.visitInsn(I2C);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false);
    }
}
