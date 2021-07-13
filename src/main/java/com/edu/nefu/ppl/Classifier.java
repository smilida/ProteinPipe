package com.edu.nefu.ppl;

import weka.classifiers.meta.LibD3C;

public class Classifier {
    public void doClassify() throws Exception{
        utils scan1 = new utils();
        System.out.print("Please input the parameter of LibD3C: ");
        String[] LibD3C_parm = scan1.getInput().split(" ");
        LibD3C.main(LibD3C_parm);
    }

    public static void main(String[] args) throws Exception{
        Classifier c = new Classifier();
        c.doClassify();
    }

}
