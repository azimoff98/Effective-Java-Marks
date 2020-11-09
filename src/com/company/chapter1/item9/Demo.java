package com.company.chapter1.item9;

import java.io.*;

public class Demo {

    private static final int BUFFER_SIZE = 512;

    static String firstLineOfFileWithResource(String path, String defaultValue){
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            return br.readLine();
        }catch (IOException e){
            return defaultValue;
        }
    }

    // try-with-resources on multiple resources - short and sweet
    static void copyWithResource(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }


    static String firstLineOfFileWithFinally(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try{
            return br.readLine();
        }finally {
            br.close();
        }
    }

    static void copyWithFinally(String src, String dst) throws IOException{
        InputStream in = new FileInputStream(src);
        try{
            OutputStream out = new FileOutputStream(dst);
            try{
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0){
                    out.write(buf, 0, n);
                }
            }finally {
                out.close();
            }
        }finally {
            in.close();
        }
    }
}
