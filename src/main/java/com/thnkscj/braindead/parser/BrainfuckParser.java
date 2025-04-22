package com.thnkscj.braindead.parser;

import com.thnkscj.braindead.program.BrainfuckProgram;

import java.io.File;

public interface BrainfuckParser {
    BrainfuckProgram parse() throws Exception;

    File filename();
}
