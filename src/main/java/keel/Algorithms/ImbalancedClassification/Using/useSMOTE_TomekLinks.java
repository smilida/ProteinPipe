package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.SMOTE_TomekLinks;

public class useSMOTE_TomekLinks {

    public void mainSMOTE_TomekLinks(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("SMOTE_TomekLinks Configuration File");
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
        System.out.println("The type of SMOTE algorithm 1:Both / 2:Minority / 3:Else");
        System.out.println("Please enter the type:");
        String SMOTEtypeChoice = scan1.getInput();
        System.out.println("Please enter the type of balancing in SMOTE[yes/no]:");
        String balance = scan1.getInput();
        System.out.println("Please enter the quantity of smoting:");
        String quantity = scan1.getInput();
        System.out.println("Is the distance function Euclidean distance?[yes/no]");
        String distanceChoice = scan1.getInput();
        String SMOTEtype,distance;
        if(SMOTEtypeChoice.equalsIgnoreCase("1")){
            SMOTEtype = "both";
        }else if(SMOTEtypeChoice.equalsIgnoreCase("2")){
            SMOTEtype = "minority";
        }else {
            SMOTEtype = "esle";
        }
        if(distanceChoice.equalsIgnoreCase("yes")){
            distance = "Euclidean";
        }else {
            distance = "else";
        }
        configFile = "SMOTE_TomekLinks_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFileSMOTETomekLinks("SMOTE_TomekLinks", trainFile, testFile,
                trainResultFile, testResultFile, seed, neighbors, SMOTEtype, balance, quantity, distance);
        SMOTE_TomekLinks smote_tomekLinks = new SMOTE_TomekLinks(configFile);
        smote_tomekLinks.run();
    }




}
