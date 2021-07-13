package com.edu.nefu.ppl;

import java.io.*;

public class CreateImblearnConfigFile {

    // Config File of AHCClustering and TomekLinks
    public void createConfigFile1(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of CPM, RandomOverSampling, RandomUnderSampling
    public void createConfigFile2(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName,
                                  String seed){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("seed=" + seed + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of CNN, CNN_TomekLinks and OSS
    public void createConfigFile3(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName,
                                  String seed, String numNeighbors){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("seed=" + seed + "\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of ADAYN, SMOTE, Safe_Level_SMOTE
    public void createConfigFile4(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName,
                                  String seed, String numNeighbors, String typeOfSMOTE,
                                  String balanceInSMOTE, String quantityOfSMOTE,
                                  String distanceFunc, String interpolation,
                                  String BLXalpha, String mu){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("seed=" + seed + "\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.write("SMOTE type=" + typeOfSMOTE + "\r\n");
            out.write("balance=" + balanceInSMOTE + "\r\n");
            out.write("quantity=" + quantityOfSMOTE + "\r\n");
            out.write("distance=" + distanceFunc + "\r\n");
            out.write("interpolation=" + interpolation + "\r\n");
            out.write("BLXalpha=" + BLXalpha + "\r\n");
            out.write("mu=" + mu + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of NCL
    public void createConfigFileNCL(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName,
                                  String numNeighbors){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of SMOTE_TomekLinks
    public void createConfigFileSMOTETomekLinks(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName,
                                  String seed, String numNeighbors, String typeOfSMOTE,
                                  String balanceInSMOTE, String quantityOfSMOTE,
                                  String distanceFunc){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("seed=" + seed + "\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.write("SMOTE type=" + typeOfSMOTE + "\r\n");
            out.write("balance=" + balanceInSMOTE + "\r\n");
            out.write("quantity=" + quantityOfSMOTE + "\r\n");
            out.write("distance=" + distanceFunc + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of ADOMS
    public void createConfigFileADOMS(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName,
                                  String seed, String numNeighbors, String typeOfSMOTE,
                                  String balanceInSMOTE, String quantityOfSMOTE,
                                  String interpolation, String BLXalpha, String mu){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("seed=" + seed + "\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.write("SMOTE type=" + typeOfSMOTE + "\r\n");
            out.write("balance=" + balanceInSMOTE + "\r\n");
            out.write("quantity=" + quantityOfSMOTE + "\r\n");
            out.write("interpolation=" + interpolation + "\r\n");
            out.write("BLXalpha=" + BLXalpha + "\r\n");
            out.write("mu=" + mu + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of SBC
    public void createConfigFileSBC(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName,
                                  String seed, String ratio, String Ncluster){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("seed=" + seed + "\r\n");
            out.write("ratio=" + ratio + "\r\n");
            out.write("Ncluster=" + Ncluster + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of SMOTE_ENN
    public void createConfigFileSMOTEENN(String imblearnName, String trainFileName, String testFileName,
                                  String trainResultFileName, String testResultFileName,
                                  String seed, String kENN,
                                  String numNeighbors, String typeOfSMOTE,
                                  String balanceInSMOTE, String quantityOfSMOTE,
                                  String distanceFunc){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("seed=" + seed + "\r\n");
            out.write("kENN=" + kENN + "\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.write("SMOTE type=" + typeOfSMOTE + "\r\n");
            out.write("balance=" + balanceInSMOTE + "\r\n");
            out.write("quantity=" + quantityOfSMOTE + "\r\n");
            out.write("distance=" + distanceFunc + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of SPIDER
    public void createConfigFileSPIDER(String imblearnName, String trainFileName, String testFileName,
                                       String trainResultFileName, String testResultFileName,
                                       String numNeighbors, String distanceFunc, String type){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.write("distance=" + distanceFunc + "\r\n");
            out.write("type=" + type + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of SPIDER2
    public void createConfigFileSPIDER2(String imblearnName, String trainFileName, String testFileName,
                                       String trainResultFileName, String testResultFileName,
                                       String numNeighbors, String distanceFunc, String relabel, String ampl){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.write("distance=" + distanceFunc + "\r\n");
            out.write("relabel=" + relabel + "\r\n");
            out.write("ampl=" + ampl + "\r\n");              // weak or strong
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }

    // Config File of Borderline_SMOTE
    public void createConfigFileBorderlineSMOTE(String imblearnName, String trainFileName, String testFileName,
                                                String trainResultFileName, String testResultFileName,
                                                String seed, String numNeighbors,String M, String typeBorderline,
                                                String typeOfSMOTE, String balanceInSMOTE,
                                                String quantityOfSMOTE, String distanceFunc,
                                                String interpolation, String BLXalpha, String mu){
        try{
            String configName = imblearnName + "_" +trainFileName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(configName));
            out.write(configName + "\r\n"); // \r\n即为换行
            out.write("input=train:\"" + trainFileName + "\",test:\"" + testFileName + "\",\r\n");
            out.write("output=train:\"" + trainResultFileName + "\",test:\"" + testResultFileName + "\",\r\n");
            out.write("seed=" + seed + "\r\n");
            out.write("neighbors=" + numNeighbors + "\r\n");
            out.write("M=" + M + "\r\n");
            out.write("typeBorderline=" + typeBorderline + "\r\n");
            out.write("SMOTE type=" + typeOfSMOTE + "\r\n");
            out.write("balance=" + balanceInSMOTE + "\r\n");
            out.write("quantity=" + quantityOfSMOTE + "\r\n");
            out.write("distance=" + distanceFunc + "\r\n");
            out.write("interpolation=" + interpolation + "\r\n");
            out.write("BLXalpha=" + BLXalpha + "\r\n");
            out.write("mu=" + mu + "\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println(configName + " was created successfully!");
        }catch (IOException e){

        }
    }
}
