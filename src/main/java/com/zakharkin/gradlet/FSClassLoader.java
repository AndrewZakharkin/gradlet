package com.zakharkin.gradlet;

import java.io.*;

public class FSClassLoader extends ClassLoader {
    private final String pathToBin;

    public FSClassLoader(String pathToBin){
        this.pathToBin = pathToBin;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte b[] = fetchFromFS(pathToBin + name.replace('.', '/')
                    + ".class");
            return defineClass(name, b, 0, b.length);
        }catch (FileNotFoundException ex){
            return super.findClass(name);
        }catch (IOException ex){
            return super.findClass(name);
        }
    }

    private byte[] fetchFromFS(String path) throws IOException{
        InputStream inp = new FileInputStream(new File(path));
        try(inp) {
            long len = new File(path).length();
            if (len > Integer.MAX_VALUE) {
                throw new InvalidClassException("File is too large");
            }
            byte [] bytes = new byte[(int)len];

            int offset = 0;
            int numRead = 0;
            while(offset < bytes.length
                    &&(numRead = inp.read(bytes, offset, bytes.length - offset)) >= 0){
                offset += numRead;
            }

            if(offset < bytes.length) {
                throw new IOException("Could not completely read file " + path);
            }
            return bytes;
        }
    }
}
