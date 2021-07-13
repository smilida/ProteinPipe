package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.RandomUnderSampling;

public class useRUS {

    public void mainRUS(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("RandomUnderSampling Configuration File");
        System.out.println("Please enter the train file name:");
        String trainFile = scan1.getInput();
        System.out.println("Please enter the test file name:");
        String testFile = scan1.getInput();
        System.out.println("Please enter the output train file name:");
        String trainResultFile = scan1.getInput();
        System.out.println("Please enter the output test file name:");
        String testResultFile = scan1.getInput();
        System.out.println("Please enter the seed of this algorithm:");
        String seed = scan1.getInput();
        configFile = "RandomUnderSampling_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFile2("RandomUnderSampling", trainFile, testFile, trainResultFile, testResultFile, seed);
        RandomUnderSampling rus = new RandomUnderSampling(configFile);
        rus.run();
    }




}
