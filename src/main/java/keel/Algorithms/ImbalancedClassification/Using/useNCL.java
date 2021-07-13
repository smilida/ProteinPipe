package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.NCL;

public class useNCL {

    public void mainNCL(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("NCL Configuration File");
        System.out.println("Please enter the train file name:");
        String trainFile = scan1.getInput();
        System.out.println("Please enter the test file name:");
        String testFile = scan1.getInput();
        System.out.println("Please enter the output train file name:");
        String trainResultFile = scan1.getInput();
        System.out.println("Please enter the output test file name:");
        String testResultFile = scan1.getInput();
        System.out.println("Please enter the number of neighbors:");
        String neighbors = scan1.getInput();
        configFile = "NCL_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFileNCL("NCL", trainFile, testFile, trainResultFile, testResultFile, neighbors);
        NCL ncl = new NCL(configFile);
        ncl.run();
    }




}
