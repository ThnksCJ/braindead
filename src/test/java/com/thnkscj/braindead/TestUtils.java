package com.thnkscj.braindead;

import com.thnkscj.braindead.program.CompiledBrainfuckProgram;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Collections;

public class TestUtils {
    public static String executeProgram(CompiledBrainfuckProgram program) throws Exception {
        InMemoryClassLoader classLoader = new InMemoryClassLoader(
                Collections.singletonMap(program.className(), program.classBytes())
        );

        Class<?> clazz = classLoader.loadClass(program.className());
        return captureOutput(() -> {
            try {
                Method main = clazz.getMethod("main", String[].class);
                main.invoke(null, (Object) new String[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static String captureOutput(Runnable code) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(outContent));
            code.run();
            return outContent.toString().trim();
        } finally {
            System.setOut(originalOut);
        }
    }
}
