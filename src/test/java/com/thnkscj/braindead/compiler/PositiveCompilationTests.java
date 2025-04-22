// PositiveCompilationTests.java
package com.thnkscj.braindead.compiler;

import com.thnkscj.braindead.TestUtils;
import com.thnkscj.braindead.compiler.impl.DefaultBrainfuckCompiler;
import com.thnkscj.braindead.parser.impl.FileBrainfuckParser;
import com.thnkscj.braindead.program.CompiledBrainfuckProgram;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PositiveCompilationTests {
    private static final String TEST_RESOURCES = "src/test/resources/";

    @Test
    void compileBasicHelloWorld() throws Exception {
        File programFile = new File(TEST_RESOURCES + "hello-world.b");
        String output = TestUtils.executeProgram(compileProgram(programFile));
        assertEquals("Hello World!", output);
    }

    @Test
    void compileWithNestedLoops() throws Exception {
        File programFile = new File(TEST_RESOURCES + "nested-loops.b");
        String output = TestUtils.executeProgram(compileProgram(programFile));
        assertTrue(output.isEmpty());
    }

    private CompiledBrainfuckProgram compileProgram(File file) {
        return new DefaultBrainfuckCompiler(new FileBrainfuckParser(file)).compile();
    }
}
