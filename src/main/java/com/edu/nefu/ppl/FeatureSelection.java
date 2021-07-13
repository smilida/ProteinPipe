package com.edu.nefu.ppl;

import datamining.xmu.edu.cn.MRMD_mulan;

public class FeatureSelection {
    static int smile_fs = 1;
    public void doFeatureSelection() throws Exception{
        try {
            utils scan1 = new utils();
            MRMD_mulan mrmd = new MRMD_mulan();
            System.out.println("Feature Selection:");
            mrmd.print_help();
            System.out.print("Please input the parameter of MRMD: ");
            String[] MRMD_parm = scan1.getInput().split(" ");
            mrmd.main(MRMD_parm);
            smile_fs = mrmd.getSmile_mrmd();
            String mrmdOutArff = mrmd.getArff();
        }catch (Exception e){
            System.out.println("Pretend nothing happened. Let's continue!");
        }

    }

    public static int getSmile_fs() {
        return smile_fs;
    }
}
