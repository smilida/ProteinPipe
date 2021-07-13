package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.ADOMS;

public class useADOMS {

    public void mainADOMS(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("ADOMS Configuration File");
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
        System.out.println("The the type of interpolation");
        System.out.println("1:Standard / 2:Uniform / 3:Arithmetical");
        System.out.println("4:Blx-alpha / 5:Sbx / 6:Fuzzy / 7:Else");
        System.out.println("Please enter the type of interpolation:");
        String interpolationChoice = scan1.getInput();
        System.out.println("Please enter the parameter alpha for BLX-alpha:");
        String alpha = scan1.getInput();
        System.out.println("Please enter the parameter mu for SBX and PBX:");
        String mu = scan1.getInput();
        String SMOTEtype, interpolation;
        if(SMOTEtypeChoice.equalsIgnoreCase("1")) {
            SMOTEtype = "both";
        }else if(SMOTEtypeChoice.equalsIgnoreCase("2")) {
            SMOTEtype = "minority";
        }else {
            SMOTEtype = "esle";
        }
        if(interpolationChoice.equalsIgnoreCase("1")) {
            interpolation = "standard";
        }else if(interpolationChoice.equalsIgnoreCase("2")) {
            interpolation = "uniform";
        }else if(interpolationChoice.equalsIgnoreCase("3")) {
            interpolation = "arithmetical";
        }else if(interpolationChoice.equalsIgnoreCase("4")) {
            interpolation = "blx-alpha";
        }else if(interpolationChoice.equalsIgnoreCase("5")) {
            interpolation = "sbx";
        }else if(interpolationChoice.equalsIgnoreCase("6")) {
            interpolation = "fuzzy";
        }else {
            interpolation = "esle";
        }
        configFile = "ADOMS_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFileADOMS("ADOMS", trainFile, testFile,
                trainResultFile, testResultFile, seed, neighbors, SMOTEtype,
                balance, quantity, interpolation, alpha, mu);
        ADOMS adoms = new ADOMS(configFile);
        adoms.run();
    }




}
