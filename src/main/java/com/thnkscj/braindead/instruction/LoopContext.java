package com.thnkscj.braindead.instruction;

import com.thnkscj.braindead.exception.UnmatchedLoopEndException;
import com.thnkscj.braindead.instruction.impl.LoopEnd;
import com.thnkscj.braindead.instruction.impl.LoopStart;

import java.util.ArrayDeque;
import java.util.Deque;

class LoopContext {
    private final Deque<LoopStart> loopStack = new ArrayDeque<>();

    public LoopStart registerLoopStart() {
        LoopStart loopStart = new LoopStart();
        loopStack.push(loopStart);
        return loopStart;
    }

    public LoopEnd resolveLoopEnd() {
        if (loopStack.isEmpty()) {
            throw new UnmatchedLoopEndException("Unmatched ']' found");
        }
        return new LoopEnd(loopStack.pop());
    }
}
