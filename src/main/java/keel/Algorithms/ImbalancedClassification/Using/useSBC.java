package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.SBC;

public class useSBC {

    public void mainSBC(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("SBC Configuration File");
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
        System.out.println("Please enter the ratio of size among majority selection with respecty minority samples:");
        String ratio = scan1.getInput();
        System.out.println("Please enter the number of cluster:");
        String Ncluster = scan1.getInput();
        configFile = "SBC_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFileSBC("SBC", trainFile, testFile,
                trainResultFile, testResultFile, seed, ratio, Ncluster);
        SBC sbc = new SBC(configFile);
        sbc.run();
    }




}
