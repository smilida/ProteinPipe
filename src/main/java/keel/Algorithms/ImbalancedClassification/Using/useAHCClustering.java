package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.utils;
import com.edu.nefu.ppl.CreateImblearnConfigFile;
import keel.Algorithms.ImbalancedClassification.Resampling.AHCClustering;

public class useAHCClustering {

    public void mainAHCClustering(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("AHCClustering Configuration File");
        System.out.println("Please enter the train file name:");
        String trainFile = scan1.getInput();
        System.out.println("Please enter the test file name:");
        String testFile = scan1.getInput();
        System.out.println("Please enter the output train file name:");
        String trainResultFile = scan1.getInput();
        System.out.println("Please enter the output test file name:");
        String testResultFile = scan1.getInput();
        configFile = "AHCClustering_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFile1("AHCClustering",trainFile,testFile,trainResultFile,testResultFile);
        AHCClustering adasyn = new AHCClustering(configFile);
        adasyn.run();
    }




}
