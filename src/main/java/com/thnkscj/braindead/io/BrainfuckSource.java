package com.thnkscj.braindead.io;

import java.io.File;

public interface BrainfuckSource {
    String read() throws Exception;

    File filename();
}
