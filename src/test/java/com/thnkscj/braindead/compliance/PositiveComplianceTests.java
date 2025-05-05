package com.thnkscj.braindead.compliance;

import com.thnkscj.braindead.TestUtils;
import com.thnkscj.braindead.compiler.impl.DefaultBrainfuckCompiler;
import com.thnkscj.braindead.io.impl.BrainfuckFileSource;
import com.thnkscj.braindead.program.CompiledBrainfuckProgram;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class PositiveComplianceTests {
    private static final String TEST_RESOURCES = "src/test/resources/compliance/";

    @Test
    void compileNegativeCells() throws Exception {
        File programFile = new File(TEST_RESOURCES + "negative-cells.b");
        String output = TestUtils.executeProgram(compileProgram(programFile));
        assertEquals("Hello World!", output);
    }

    private CompiledBrainfuckProgram compileProgram(File file) {
        return new DefaultBrainfuckCompiler(new BrainfuckFileSource(file)).compile();
    }
}
