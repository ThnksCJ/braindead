package com.thnkscj.braindead;

import com.thnkscj.braindead.compiler.BrainfuckCompiler;
import com.thnkscj.braindead.compiler.BrainfuckCompilerPool;
import com.thnkscj.braindead.io.BrainfuckSource;
import com.thnkscj.braindead.io.impl.BrainfuckFileSource;
import com.thnkscj.braindead.program.CompiledBrainfuckProgram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java -jar Braindead.jar <input.b>");
            System.exit(1);
        }

        BrainfuckSource parser = new BrainfuckFileSource(new File(args[0]));
        BrainfuckCompiler compiler = BrainfuckCompilerPool.getCompiler(parser);
        CompiledBrainfuckProgram compiledBrainfuckProgram = compiler.compile();

        String outputFileName = compiledBrainfuckProgram.className() + ".class";
        Files.write(Paths.get(outputFileName), compiledBrainfuckProgram.classBytes());
        System.out.println("Compilation successful! Bytecode saved to: " + outputFileName);
    }
}
