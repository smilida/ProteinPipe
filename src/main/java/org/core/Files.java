//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.core;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Files {
    public Files() {
    }

    public static String readFile(String fileName) {
        String content = "";

        try {
            FileInputStream fis = new FileInputStream(fileName);
            byte[] piece = new byte[4096];
            int readBytes = 0;

            while(readBytes != -1) {
                readBytes = fis.read(piece);
                if (readBytes != -1) {
                    content = content + new String(piece, 0, readBytes);
                }
            }

            fis.close();
        } catch (IOException var5) {
            var5.printStackTrace();
            System.exit(-1);
        }

        return content;
    }

    public static void writeFile(String fileName, String content) {
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            DataOutputStream fis = new DataOutputStream(f);
            fis.writeBytes(content);
            fis.close();
        } catch (IOException var4) {
            var4.printStackTrace();
            System.exit(-1);
        }

    }

    public static void addToFile(String fileName, String content) {
        try {
            RandomAccessFile fis = new RandomAccessFile(fileName, "rw");
            fis.seek(fis.length());
            fis.writeBytes(content);
            fis.close();
        } catch (IOException var3) {
            var3.printStackTrace();
            System.exit(-1);
        }

    }
}
