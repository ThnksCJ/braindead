package com.thnkscj.braindead.instruction;

import org.objectweb.asm.MethodVisitor;

public interface BrainfuckInstruction {
    void execute(MethodVisitor mv);
}
