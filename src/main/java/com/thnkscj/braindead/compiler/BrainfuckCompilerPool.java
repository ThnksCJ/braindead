package com.thnkscj.braindead.compiler;

import com.thnkscj.braindead.compiler.impl.DefaultBrainfuckCompiler;
import com.thnkscj.braindead.io.BrainfuckSource;

import java.util.HashMap;
import java.util.Map;

public final class BrainfuckCompilerPool {
    private static final Map<BrainfuckSource, BrainfuckCompiler> pool = new HashMap<>();

    public static synchronized BrainfuckCompiler getCompiler(BrainfuckSource parser) {
        return pool.computeIfAbsent(parser, DefaultBrainfuckCompiler::new);
    }
}
