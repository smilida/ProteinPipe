package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.CNN_TomekLinks;

public class useCNN_TomekLinks {

    public void mainCNN_TomekLinks(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("CNN_TomekLinks Configuration File");
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
        System.out.println("Please enter the number of neighbors:");
        String neighbors = scan1.getInput();
        configFile = "CNN_TomekLinks_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFile3("CNN_TomekLinks", trainFile, testFile, trainResultFile, testResultFile, seed, neighbors);
        CNN_TomekLinks cnn_tomekLinks = new CNN_TomekLinks(configFile);
        cnn_tomekLinks.run();
    }




}
