package com.thnkscj.braindead;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class InMemoryClassLoader extends ClassLoader {
    private final Map<String, byte[]> classData;
    private final File outputDir;
    private final boolean cacheToDisk;

    public InMemoryClassLoader(Map<String, byte[]> classData, boolean cacheToDisk) {
        this.classData = classData;
        this.cacheToDisk = cacheToDisk;
        this.outputDir = new File("generated_classes");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = classData.get(name);
        if (bytes == null) throw new ClassNotFoundException(name);

        cacheClassToDisk(name, bytes);

        return defineClass(name, bytes, 0, bytes.length);
    }

    private void cacheClassToDisk(String className, byte[] bytes) {
        if (outputDir == null || !outputDir.exists() || !cacheToDisk) {
            return;
        }

        try {
            String classPath = className.replace('.', '/') + ".class";
            File classFile = new File(outputDir, classPath);
            File parent = classFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (FileOutputStream fos = new FileOutputStream(classFile)) {
                fos.write(bytes);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to cache class to disk: " + className, e);
        }
    }
}
