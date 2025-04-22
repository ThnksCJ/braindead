package com.thnkscj.braindead.compiler.impl;

import com.thnkscj.braindead.compiler.BrainfuckCompiler;
import com.thnkscj.braindead.parser.BrainfuckParser;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public abstract class AsmBrainfuckCompiler extends BrainfuckCompiler {
    private static final int MEMORY_SIZE = 30000;

    public AsmBrainfuckCompiler(BrainfuckParser parser) {
        super(parser);
    }

    @Override
    protected void createClassStructure() {
        classWriter.visit(V1_8, ACC_PUBLIC + ACC_SUPER,
                className, null, "java/lang/Object", null);

        MethodVisitor mv = classWriter.visitMethod(
                ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                new String[]{"java/io/IOException"}
        );
        this.methodVisitor = mv;
        mv.visitCode();
    }

    @Override
    protected void setupExecutionEnvironment() {
        methodVisitor.visitIntInsn(SIPUSH, MEMORY_SIZE);
        methodVisitor.visitIntInsn(NEWARRAY, T_BYTE);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitVarInsn(ISTORE, 2);
    }
}
