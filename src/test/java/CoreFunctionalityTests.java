import com.thnkscj.braindead.compiler.BrainfuckCompiler;
import com.thnkscj.braindead.compiler.BrainfuckCompilerPool;
import com.thnkscj.braindead.io.BrainfuckSource;
import com.thnkscj.braindead.io.impl.BrainfuckFileSource;
import com.thnkscj.braindead.program.CompiledBrainfuckProgram;

import com.thnkscj.braindead.InMemoryClassLoader;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CoreFunctionalityTests {
    @Test
    public void canCompileAndRunHelloWorld() throws Exception {
        String inputFilePath = "src/test/resources/hello-world.b";
        String expectedOutput = "Hello World!";

        BrainfuckSource parser = new BrainfuckFileSource(new File(inputFilePath));
        BrainfuckCompiler compiler = BrainfuckCompilerPool.getCompiler(parser);
        CompiledBrainfuckProgram compiledProgram = compiler.compile();

        String className = compiledProgram.className();
        byte[] classBytes = compiledProgram.classBytes();

        writeToFile(className + ".class", classBytes);

        Map<String, byte[]> classMap = new HashMap<>();
        classMap.put(className, classBytes);

        InMemoryClassLoader classLoader = new InMemoryClassLoader(classMap);
        Class<?> clazz = classLoader.loadClass(className);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Method mainMethod = clazz.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString().trim();
        assert output.equals(expectedOutput) : "Expected: " + expectedOutput + ", but got: " + output;
    }

    public void writeToFile(String filePath, byte[] content) {
        try {
            Files.write(Paths.get(filePath), content);
        } catch (Exception e) {

        }
    }
}
