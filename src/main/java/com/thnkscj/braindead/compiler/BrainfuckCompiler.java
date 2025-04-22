package com.thnkscj.braindead.compiler;

import com.thnkscj.braindead.exception.CompilerException;
import com.thnkscj.braindead.instruction.BrainfuckInstruction;
import com.thnkscj.braindead.parser.BrainfuckParser;
import com.thnkscj.braindead.program.BrainfuckProgram;
import com.thnkscj.braindead.program.CompiledBrainfuckProgram;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.List;

import static org.objectweb.asm.Opcodes.*;

public class BrainfuckCompiler {

    private static final int MEMORY_SIZE = 30000;

    private final BrainfuckParser parser;

    public BrainfuckCompiler(BrainfuckParser parser) {
        this.parser = parser;
    }

    public CompiledBrainfuckProgram compile() {
        try {
            BrainfuckProgram program = parser.parse();
            List<BrainfuckInstruction> instructions = program.instructions();

            String className = generateClassName();
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classWriter.visit(V1_8, ACC_PUBLIC + ACC_SUPER, className, null, "java/lang/Object", null);

            MethodVisitor methodVisitor = classWriter.visitMethod(
                    ACC_PUBLIC + ACC_STATIC,
                    "main",
                    "([Ljava/lang/String;)V",
                    null,
                    new String[]{"java/io/IOException"}
            );
            methodVisitor.visitCode();

            methodVisitor.visitIntInsn(SIPUSH, MEMORY_SIZE);
            methodVisitor.visitIntInsn(NEWARRAY, T_BYTE);
            methodVisitor.visitVarInsn(ASTORE, 1);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 2);

            for (BrainfuckInstruction instruction : instructions) {
                instruction.execute(methodVisitor);
            }

            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(0, 0);
            methodVisitor.visitEnd();

            classWriter.visitEnd();

            return new CompiledBrainfuckProgram(className, classWriter.toByteArray());
        } catch (Exception e) {
            throw new CompilerException("Compilation failed: " + e.getMessage(), e);
        }
    }

    private String generateClassName() {
        return parser.filename().getName().replace(".bf", "").replaceAll("[^A-Za-z0-9]", "_");
    }
}
