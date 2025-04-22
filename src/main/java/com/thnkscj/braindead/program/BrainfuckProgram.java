package com.thnkscj.braindead.program;

import com.thnkscj.braindead.instruction.BrainfuckInstruction;

import java.util.List;

public record BrainfuckProgram(List<BrainfuckInstruction> instructions) {
}
