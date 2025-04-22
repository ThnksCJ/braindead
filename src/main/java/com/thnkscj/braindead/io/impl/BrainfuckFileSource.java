package com.thnkscj.braindead.io.impl;

import com.thnkscj.braindead.io.BrainfuckSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class BrainfuckFileSource implements BrainfuckSource {
    private final File file;

    public BrainfuckFileSource(File file) {
        this.file = file;
    }

    @Override
    public String read() throws IOException {
        return Files.readString(file.toPath());
    }

    @Override
    public File filename() {
        return file;
    }
}
