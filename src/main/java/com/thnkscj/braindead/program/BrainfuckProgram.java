package com.thnkscj.braindead.program;

import com.thnkscj.braindead.instruction.BraindeadInstruction;

import java.util.List;

public record BrainfuckProgram(List<BraindeadInstruction> instructions) {
}
