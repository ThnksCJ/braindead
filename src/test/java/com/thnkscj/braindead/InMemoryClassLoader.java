package com.thnkscj.braindead;

import java.util.Map;

public class InMemoryClassLoader extends ClassLoader {
    private final Map<String, byte[]> classData;

    public InMemoryClassLoader(Map<String, byte[]> classData) {
        this.classData = classData;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = classData.get(name);
        if (bytes == null) throw new ClassNotFoundException(name);
        return defineClass(name, bytes, 0, bytes.length);
    }
}
