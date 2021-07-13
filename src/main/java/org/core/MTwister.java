//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.core;

public class MTwister {
    long[] state = new long[624];
    int left = 1;
    int initf = 0;
    int inext;

    public MTwister() {
    }

    public MTwister(long s) {
        this.init_genrand(s);
    }

    public MTwister(long[] init_key) {
        this.init_by_array(init_key);
    }

    public void init_genrand(long s) {
        this.state[0] = s & 4294967295L;

        for(int j = 1; j < 624; ++j) {
            this.state[j] = 1812433253L * (this.state[j - 1] ^ this.state[j - 1] >> 30) + (long)j;
            long[] var10000 = this.state;
            var10000[j] &= 4294967295L;
        }

        this.left = 1;
        this.initf = 1;
    }

    void init_by_array(long[] init_key) {
        int key_length = init_key.length;
        this.init_genrand(19650218L);
        int i = 1;
        int j = 0;

        long[] var10000;
        int k;
        for(k = 624 > key_length ? 624 : key_length; k > 0; --k) {
            this.state[i] = (this.state[i] ^ (this.state[i - 1] ^ this.state[i - 1] >> 30) * 1664525L) + init_key[j] + (long)j;
            var10000 = this.state;
            var10000[i] &= 4294967295L;
            ++i;
            ++j;
            if (i >= 624) {
                this.state[0] = this.state[623];
                i = 1;
            }

            if (j >= key_length) {
                j = 0;
            }
        }

        for(k = 623; k > 0; --k) {
            this.state[i] = (this.state[i] ^ (this.state[i - 1] ^ this.state[i - 1] >> 30) * 1566083941L) - (long)i;
            var10000 = this.state;
            var10000[i] &= 4294967295L;
            ++i;
            if (i >= 624) {
                this.state[0] = this.state[623];
                i = 1;
            }
        }

        this.state[0] = 2147483648L;
        this.left = 1;
        this.initf = 1;
    }

    void next_state() {
        int ip = 0;
        if (this.initf == 0) {
            this.init_genrand(5489L);
        }

        this.left = 624;
        this.inext = 0;
        int j = 228;

        while(true) {
            --j;
            if (j <= 0) {
                j = 397;

                while(true) {
                    --j;
                    if (j <= 0) {
                        this.state[ip] = this.state[ip + 397 - 624] ^ (this.state[ip + 0] & 2147483648L | this.state[0] & 2147483647L) >> 1 ^ ((this.state[0] & 1L) != 0L ? 2567483615L : 0L);
                        return;
                    }

                    this.state[ip] = this.state[ip + 397 - 624] ^ (this.state[ip + 0] & 2147483648L | this.state[ip + 1] & 2147483647L) >> 1 ^ ((this.state[ip + 1] & 1L) != 0L ? 2567483615L : 0L);
                    ++ip;
                }
            }

            this.state[ip] = this.state[ip + 397] ^ (this.state[ip + 0] & 2147483648L | this.state[ip + 1] & 2147483647L) >> 1 ^ ((this.state[ip + 1] & 1L) != 0L ? 2567483615L : 0L);
            ++ip;
        }
    }

    long genrand_int32() {
        if (--this.left == 0) {
            this.next_state();
        }

        long y = this.state[this.inext++];
        y ^= y >> 11;
        y ^= y << 7 & 2636928640L;
        y ^= y << 15 & 4022730752L;
        y ^= y >> 18;
        return y;
    }

    long genrand_int31() {
        if (--this.left == 0) {
            this.next_state();
        }

        long y = this.state[this.inext++];
        y ^= y >> 11;
        y ^= y << 7 & 2636928640L;
        y ^= y << 15 & 4022730752L;
        y ^= y >> 18;
        return y >> 1;
    }

    public double genrand_real1() {
        if (--this.left == 0) {
            this.next_state();
        }

        long y = this.state[this.inext++];
        y ^= y >> 11;
        y ^= y << 7 & 2636928640L;
        y ^= y << 15 & 4022730752L;
        y ^= y >> 18;
        return (double)y * 2.3283064370807974E-10D;
    }

    public double genrand_real2() {
        if (--this.left == 0) {
            this.next_state();
        }

        long y = this.state[this.inext++];
        y ^= y >> 11;
        y ^= y << 7 & 2636928640L;
        y ^= y << 15 & 4022730752L;
        y ^= y >> 18;
        return (double)y * 2.3283064365386963E-10D;
    }

    public double genrand_real3() {
        if (--this.left == 0) {
            this.next_state();
        }

        long y = this.state[this.inext++];
        y ^= y >> 11;
        y ^= y << 7 & 2636928640L;
        y ^= y << 15 & 4022730752L;
        y ^= y >> 18;
        return ((double)y + 0.5D) * 2.3283064365386963E-10D;
    }

    public double genrand_res53() {
        long a = this.genrand_int32() >> 5;
        long b = this.genrand_int32() >> 6;
        return ((double)a * 6.7108864E7D + (double)b) * 1.1102230246251565E-16D;
    }

    public double genrand_gaussian() {
        double a = 0.0D;

        for(int i = 0; i < 6; ++i) {
            a += this.genrand_real1();
            a -= this.genrand_real1();
        }

        return a;
    }

    public long[] getState() {
        long[] savedState = new long[627];

        for(int i = 0; i < 624; ++i) {
            savedState[i] = this.state[i];
        }

        savedState[624] = (long)this.left;
        savedState[625] = (long)this.initf;
        savedState[626] = (long)this.inext;
        return savedState;
    }

    public void setState(long[] savedState) {
        for(int i = 0; i < 624; ++i) {
            this.state[i] = savedState[i];
        }

        this.left = (int)savedState[624];
        this.initf = (int)savedState[625];
        this.inext = (int)savedState[626];
    }
}
