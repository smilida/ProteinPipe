package keel.Algorithms.ImbalancedClassification.Using;

import com.edu.nefu.ppl.CreateImblearnConfigFile;
import com.edu.nefu.ppl.utils;
import keel.Algorithms.ImbalancedClassification.Resampling.TomekLinks;

public class useTomekLinks {

    public void mainTomekLinks(){
        String configFile;
        utils scan1 = new utils();
        System.out.println("TomekLinks Configuration File");
        System.out.println("Please enter the train file name:");
        String trainFile = scan1.getInput();
        System.out.println("Please enter the test file name:");
        String testFile = scan1.getInput();
        System.out.println("Please enter the output train file name:");
        String trainResultFile = scan1.getInput();
        System.out.println("Please enter the output test file name:");
        String testResultFile = scan1.getInput();
        configFile = "TomekLinks_" + trainFile + ".txt";
        CreateImblearnConfigFile createFile = new CreateImblearnConfigFile();
        createFile.createConfigFile1("TomekLinks",trainFile,testFile,trainResultFile,testResultFile);
        TomekLinks adasyn = new TomekLinks(configFile);
        adasyn.run();
    }




}
