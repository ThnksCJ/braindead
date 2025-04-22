package com.thnkscj.braindead.parser.impl;

import com.thnkscj.braindead.instruction.BrainfuckInstruction;
import com.thnkscj.braindead.instruction.BrainfuckInstructionFactory;
import com.thnkscj.braindead.parser.BrainfuckParser;
import com.thnkscj.braindead.program.BrainfuckProgram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public final class FileBrainfuckParser implements BrainfuckParser {
    private static final String VALID_COMMANDS = "><+-.,[]";
    private final File file;

    public FileBrainfuckParser(File file) {
        this.file = file;
    }

    @Override
    public BrainfuckProgram parse() throws IOException {
        String sourceCode = Files.readString(file.toPath());
        List<BrainfuckInstruction> instructions = new ArrayList<>();
        BrainfuckInstructionFactory factory = new BrainfuckInstructionFactory();

        for (char ch : sourceCode.toCharArray()) {
            if (isValidBrainfuckCommand(ch)) {
                instructions.add(factory.createInstruction(ch));
            }
        }

        return new BrainfuckProgram(instructions);
    }

    @Override
    public File filename() {
        return file;
    }

    private boolean isValidBrainfuckCommand(char ch) {
        return VALID_COMMANDS.indexOf(ch) != -1;
    }
}
