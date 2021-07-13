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

public class Fichero {
    public Fichero() {
    }

    public static String leeFichero(String nombreFichero) {
        String cadena = "";

        try {
            FileInputStream fis = new FileInputStream(nombreFichero);
            byte[] leido = new byte[4096];
            int bytesLeidos = 0;

            while(bytesLeidos != -1) {
                bytesLeidos = fis.read(leido);
                if (bytesLeidos != -1) {
                    cadena = cadena + new String(leido, 0, bytesLeidos);
                }
            }

            fis.close();
        } catch (IOException var5) {
            var5.printStackTrace();
            System.exit(-1);
        }

        return cadena;
    }

    public static void escribeFichero(String nombreFichero, String cadena) {
        try {
            FileOutputStream f = new FileOutputStream(nombreFichero);
            DataOutputStream fis = new DataOutputStream(f);
            fis.writeBytes(cadena);
            fis.close();
        } catch (IOException var4) {
            var4.printStackTrace();
            System.exit(-1);
        }

    }

    public static void AnadirtoFichero(String nombreFichero, String cadena) {
        try {
            RandomAccessFile fis = new RandomAccessFile(nombreFichero, "rw");
            fis.seek(fis.length());
            fis.writeBytes(cadena);
            fis.close();
        } catch (IOException var3) {
            var3.printStackTrace();
            System.exit(-1);
        }

    }
}
