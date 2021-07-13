//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.core;

public class Randomize {
    private static long Seed;
    private static MTwister generador = new MTwister();

    public Randomize() {
    }

    public static void setSeed(long semilla) {
        Seed = semilla;
        generador.init_genrand(Seed);
    }

    public static double Rand() {
        return generador.genrand_res53();
    }

    public static double RandOpen() {
        return generador.genrand_real3();
    }

    public static double RandClosed() {
        return generador.genrand_real1();
    }

    public static double RandGaussian() {
        return generador.genrand_gaussian();
    }

    public static int Randint(int low, int high) {
        return (int)((double)low + (double)(high - low) * generador.genrand_res53());
    }

    public static int RandintOpen(int low, int high) {
        return (int)((double)(low + 1) + (double)(high - (low + 1)) * generador.genrand_res53());
    }

    public static int RandintClosed(int low, int high) {
        return (int)((double)low + (double)(high + 1 - low) * generador.genrand_res53());
    }

    public static double Randdouble(double low, double high) {
        return low + (high - low) * generador.genrand_res53();
    }

    public static double RanddoubleOpen(double low, double high) {
        return low + (high - low) * generador.genrand_real3();
    }

    public static double RanddoubleClosed(double low, double high) {
        return low + (high - low) * generador.genrand_real1();
    }
}
