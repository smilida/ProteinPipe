package com.edu.nefu.ppl;

import sequencetolibsvmvector.Flow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FeatureExtraction {
    static int smile_fe = 1;
    public void doFeatureExtract(){
        // raw data提取188D特征后输出文件，且输出文件的label改为数字类型
        // 188D Feature Extraction
        smile_fe = 0;
        utils scan1 = new utils();
        System.out.println("Feature extraction");
        tipsInfo();
        System.out.println("Please enter the input fasta/fastq files' name:");
        String[] train_fastq_name = scan1.getInput().split(" ");
        boolean flag_input = true;
        for(int i = 0; i < train_fastq_name.length; ++i){
            File file = new File(train_fastq_name[i]);
            if(!file.exists()){
                System.out.println(train_fastq_name[i] + " is not exist. Please check!");
                flag_input = false;
                smile_fe = 1;
            }
        }
        if(flag_input){
            System.out.println("Please enter the output Arff file name:");
            String train_arffName = scan1.getInput();
            if(train_arffName.endsWith(".arff")){
                Flow f = new Flow(train_fastq_name);
                try (PrintStream ps = new PrintStream(new FileOutputStream(train_arffName))) {
                    PrintStream out = System.out;
                    System.setOut(ps);
                    f.run();
                    for(int i=0; i< train_fastq_name.length; i++){
                        utils.modifyFile(train_arffName, "class"+String.valueOf(i+1), String.valueOf(i));
                    }
                    System.setOut(out);
                    System.out.println("Gotten " + train_arffName + "!");
                } catch (IOException e) {
                    System.err.println("Error in writing to file");
                }
            }else{
                System.out.println("Sorry! The output file must be a file in arff format.");
                smile_fe = 1;
            }
        }
    }

    public void tipsInfo(){
        System.out.println("Tips:");
        System.out.println("The input files must be in fasta/fastq format.");
        System.out.println("Different classes of data are stored in different files.");
    }

    public int getSmile_fe(){
        return smile_fe;
    }

    public static void setSmile_fe(int smile_fe) {
        FeatureExtraction.smile_fe = smile_fe;
    }
}
