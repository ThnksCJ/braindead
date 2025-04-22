package com.thnkscj.braindead.compiler;

import com.thnkscj.braindead.compiler.impl.DefaultBrainfuckCompiler;
import com.thnkscj.braindead.exception.CompilerException;
import com.thnkscj.braindead.parser.impl.FileBrainfuckParser;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NegativeCompilationTests {
    private static final String TEST_RESOURCES = "src/test/resources/invalid/";

    @Test
    void rejectUnmatchedLoopStart() {
        File programFile = new File(TEST_RESOURCES + "unmatched-start.b");
        assertThrows(CompilerException.class, () -> compileProgram(programFile));
    }

    @Test
    void rejectUnmatchedLoopEnd() {
        File programFile = new File(TEST_RESOURCES + "unmatched-end.b");
        assertThrows(CompilerException.class, () -> compileProgram(programFile));
    }

    private void compileProgram(File file) {
        new DefaultBrainfuckCompiler(new FileBrainfuckParser(file)).compile();
    }
}
