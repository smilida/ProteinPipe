package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.SPIDER;

public class useSPIDER {

    public void mainSPIDER(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("SPIDER Configuration File");
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
        System.out.println("Is the distance function Euclidean distance?[yes/no]");
        String distanceChoice = scan1.getInput();
        System.out.println("The preprocess type 1:WEAK / 2:RELABEL / 3:STRONG");
        System.out.println("Please enter the preprocess type:");
        String typeChoice = scan1.getInput();
        String distance;
        String type;
        if(distanceChoice.equalsIgnoreCase("yes")){
            distance = "Euclidean";
        }else {
            distance = "else";
        }
        if(typeChoice.equalsIgnoreCase("1")){
            type = "WEAK";
        }else if(typeChoice.equalsIgnoreCase("2")){
            type = "RELABEL";
        }else {
            type = "STRONG";
        }
        configFile = "SPIDER_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFileSPIDER("SPIDER", trainFile, testFile,
                trainResultFile, testResultFile, neighbors, distance, type);
        SPIDER spider = new SPIDER(configFile);
        spider.run();
    }




}
