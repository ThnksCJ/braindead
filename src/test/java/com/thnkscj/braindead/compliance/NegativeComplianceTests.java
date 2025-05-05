package com.thnkscj.braindead.compliance;

import com.thnkscj.braindead.TestUtils;
import com.thnkscj.braindead.compiler.impl.DefaultBrainfuckCompiler;
import com.thnkscj.braindead.io.impl.BrainfuckFileSource;
import com.thnkscj.braindead.program.CompiledBrainfuckProgram;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class NegativeComplianceTests {
    private static final String TEST_RESOURCES = "src/test/resources/compliance/";

    @Test
    void compile4LHelloWorld() throws Exception {
        File programFile = new File(TEST_RESOURCES + "4L-hello-world.b");

        Throwable cause = TestUtils.getRootCause(assertThrows(RuntimeException.class, () ->
                TestUtils.executeProgram(compileProgram(programFile))
        ));

        assertNotNull(cause, "Expected ArrayIndexOutOfBoundsException to be the root cause");
        assertInstanceOf(ArrayIndexOutOfBoundsException.class, cause);
    }

    @Test
    void compile5LHelloWorld() throws Exception {
        File programFile = new File(TEST_RESOURCES + "5L-hello-world.b");

        Throwable cause = TestUtils.getRootCause(assertThrows(RuntimeException.class, () ->
                TestUtils.executeProgram(compileProgram(programFile))
        ));

        assertNotNull(cause, "Expected ArrayIndexOutOfBoundsException to be the root cause");
        assertInstanceOf(ArrayIndexOutOfBoundsException.class, cause);
    }

    private CompiledBrainfuckProgram compileProgram(File file) {
        return new DefaultBrainfuckCompiler(new BrainfuckFileSource(file)).compile();
    }
}
