package com.thnkscj.braindead.parser.impl;

import com.thnkscj.braindead.instruction.BrainfuckInstruction;
import com.thnkscj.braindead.instruction.BrainfuckInstructionFactory;
import com.thnkscj.braindead.parser.BrainfuckParser;
import com.thnkscj.braindead.program.BrainfuckProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record FileBrainfuckParser(File filename) implements BrainfuckParser {
    @Override
    public BrainfuckProgram parse() throws IOException {
        List<BrainfuckInstruction> instructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int ch;
            while ((ch = br.read()) != -1) {
                if (Character.isWhitespace(ch))
                    continue;

                BrainfuckInstruction instruction = BrainfuckInstructionFactory.getInstruction((char) ch);
                instructions.add(instruction);
            }
        }
        return new BrainfuckProgram(instructions);
    }
}
