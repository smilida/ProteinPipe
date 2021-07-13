package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.SPIDER2;

public class useSPIDER2 {

    public void mainSPIDER2(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("SPIDER2 Configuration File");
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
        System.out.println("Please the relabeling option[yes/no]:");
        String relabelChoice = scan1.getInput();
        System.out.println("The Ampl option 1:WEAK / 2:STRONG");
        System.out.println("Please enter the ampl option:");
        String amplChoice = scan1.getInput();
        String distance, relabel, ampl;
        if(distanceChoice.equalsIgnoreCase("yes")){
            distance = "Euclidean";
        }else {
            distance = "else";
        }
        if(relabelChoice.equalsIgnoreCase("yes")){
            relabel = "true";
        }else {
            relabel = "false";
        }
        if(amplChoice.equalsIgnoreCase("1")){
            ampl = "WEAK";
        }else {
            ampl = "STRONG";
        }
        configFile = "SPIDER2_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFileSPIDER2("SPIDER2", trainFile, testFile,
                trainResultFile, testResultFile, neighbors, distance, relabel, ampl);
        SPIDER2 spider2 = new SPIDER2(configFile);
        spider2.run();
    }




}
