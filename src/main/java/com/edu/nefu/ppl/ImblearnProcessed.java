package com.edu.nefu.ppl;

import keel.Algorithms.ImbalancedClassification.Using.*;

public class ImblearnProcessed {

    public void doImblearn() throws Exception{
        System.out.println("Imbalanced data Process:");
        ImblearnAlgorithmList();
        System.out.print("Please enter the number of algorithm:");
        utils scan1 = new utils();
        String algorithmNum = scan1.getInput();
        System.out.println("-*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*-");
        chooseImblearnAlgorithm(Integer.parseInt(algorithmNum));
    }

    public static void ImblearnAlgorithmList(){
        System.out.println("-*--*--*--*--*-Imbalance Algorithm List-*--*--*--*--*-");
        System.out.println("-*-   1: ADASYN                                    -*-");
        System.out.println("-*-   2: ADOMS                                     -*-");
        System.out.println("-*-   3: AHCClustering                             -*-");
        System.out.println("-*-   4: Borderline_SMOTE                          -*-");
        System.out.println("-*-   5: CNN                                       -*-");
        System.out.println("-*-   6: CNN_TomekLinks                            -*-");
        System.out.println("-*-   7: CPM                                       -*-");
        System.out.println("-*-   8: NCL                                       -*-");
        System.out.println("-*-   9: OSS                                       -*-");
        System.out.println("-*-   10: RandomOverSampling                        -*-");
        System.out.println("-*-   11: RandomUnderSampling                      -*-");
        System.out.println("-*-   12: Safe_Level_SMOTE                         -*-");
        System.out.println("-*-   13: SBC                                      -*-");
        System.out.println("-*-   14: SMOTE                                    -*-");
        System.out.println("-*-   15: SMOTE_ENN                                -*-");
        System.out.println("-*-   16: SMOTE_TomekLinks                         -*-");
        System.out.println("-*-   17: SPIDER                                   -*-");
        System.out.println("-*-   18: SPIDER2                                  -*-");
        System.out.println("-*-   19: TomekLinks                               -*-");
        System.out.println("-*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*-");
    }


    public static void chooseImblearnAlgorithm(int i){
        switch (i){
            case 1:{
                useADASYN useadasyn = new useADASYN();
                useadasyn.mainADASYN();
                break;
            }
            case 2:{
                useADOMS useadoms = new useADOMS();
                useadoms.mainADOMS();
                break;
            }
            case 3:{
                useAHCClustering useAHCC = new useAHCClustering();
                useAHCC.mainAHCClustering();
                break;
            }
            case 4:{
                useBorderline_SMOTE useBorderline_smote = new useBorderline_SMOTE();
                useBorderline_smote.mainBorderline_SMOTE();
                break;
            }
            case 5:{
                useCNN usecnn = new useCNN();
                usecnn.mainCNN();
                break;
            }
            case 6:{
                useCNN_TomekLinks usecnnTomekLinks = new useCNN_TomekLinks();
                usecnnTomekLinks.mainCNN_TomekLinks();
                break;
            }
            case 7:{
                useCPM usecpm = new useCPM();
                usecpm.mainCPM();
                break;
            }
            case 8:{
                useNCL usencl = new useNCL();
                usencl.mainNCL();
                break;
            }
            case 9:{
                useOSS useoss = new useOSS();
                useoss.mainOSS();
                break;
            }
            case 10:{
                useROS useros = new useROS();
                useros.mainROS();
                break;
            }
            case 11:{
                useRUS userus = new useRUS();
                userus.mainRUS();
                break;
            }
            case 12:{
                useSafe_Level_SMOTE useSafe_level_smote = new useSafe_Level_SMOTE();
                useSafe_level_smote.mainSafe_Level_SMOTE();
                break;
            }
            case 13:{
                useSBC usesbc = new useSBC();
                usesbc.mainSBC();
                break;
            }
            case 14:{
                useSMOTE usesmote = new useSMOTE();
                usesmote.mainSMOTE();
                break;
            }
            case 15:{
                useSMOTE_ENN useSMOTE_enn = new useSMOTE_ENN();
                useSMOTE_enn.mainSMOTE_ENN();
                break;
            }
            case 16:{
                useSMOTE_TomekLinks useSMOTETomekLinks = new useSMOTE_TomekLinks();
                useSMOTETomekLinks.mainSMOTE_TomekLinks();
                break;
            }
            case 17:{
                useSPIDER usespider = new useSPIDER();
                usespider.mainSPIDER();
                break;
            }
            case 18:{
                useSPIDER2 usespider2 = new useSPIDER2();
                usespider2.mainSPIDER2();
                break;
            }
            case 19:{
                useTomekLinks useTomek = new useTomekLinks();
                useTomek.mainTomekLinks();
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        ImblearnProcessed imblearnProcessed = new ImblearnProcessed();
        imblearnProcessed.doImblearn();
    }
}
