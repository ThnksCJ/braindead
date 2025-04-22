package com.thnkscj.braindead.compiler;

import com.thnkscj.braindead.exception.CompilerException;
import com.thnkscj.braindead.parser.BrainfuckParser;
import com.thnkscj.braindead.program.CompiledBrainfuckProgram;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.RETURN;

public abstract class BrainfuckCompiler {
    protected final BrainfuckParser parser;
    protected ClassWriter classWriter;
    protected MethodVisitor methodVisitor;
    protected String className;

    public BrainfuckCompiler(BrainfuckParser parser) {
        this.parser = parser;
    }

    public CompiledBrainfuckProgram compile() {
        try {
            initializeCompilation();
            createClassStructure();
            setupExecutionEnvironment();
            processInstructions();
            finalizeCompilation();
            return buildOutput();
        } catch (Exception e) {
            handleCompilationError(e);
            return null;
        }
    }

    protected void initializeCompilation() {
        this.className = generateClassName();
        this.classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    }

    protected abstract void createClassStructure();

    protected abstract void setupExecutionEnvironment();

    protected abstract void processInstructions() throws IOException;

    protected void finalizeCompilation() {
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
        classWriter.visitEnd();
    }

    protected CompiledBrainfuckProgram buildOutput() {
        return new CompiledBrainfuckProgram(className, classWriter.toByteArray());
    }

    protected void handleCompilationError(Exception e) {
        throw new CompilerException("Compilation failed: " + e.getMessage(), e);
    }

    protected String generateClassName() {
        return parser.filename().getName()
                .replaceAll("\\.[^.]+$", "")
                .replaceAll("[^A-Za-z0-9]", "_");
    }
}
