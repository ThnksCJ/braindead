package com.thnkscj.braindead.compiler;

import com.thnkscj.braindead.compiler.impl.DefaultBrainfuckCompiler;
import com.thnkscj.braindead.parser.BrainfuckParser;

import java.util.HashMap;
import java.util.Map;

public final class BrainfuckCompilerPool {
    private static final Map<BrainfuckParser, BrainfuckCompiler> pool = new HashMap<>();

    public static synchronized BrainfuckCompiler getCompiler(BrainfuckParser parser) {
        return pool.computeIfAbsent(parser, DefaultBrainfuckCompiler::new);
    }
}
