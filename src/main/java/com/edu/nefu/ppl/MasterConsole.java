package com.edu.nefu.ppl;

public class MasterConsole {
    static int smile = 1;
    public static void main(String[] args) throws Exception {
        while(smile!=0){
            welcomeInfo();
            System.out.print("Please enter the number of function:");
            utils scan1 = new utils();
            String functionNum = scan1.getInput();
            System.out.println("-*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*-");
            CaseTest(Integer.parseInt(functionNum));
        }
    }

    public static void welcomeInfo(){
        System.out.println("-*--*--*--*--*-Welcome  to  ProteinPipe-*--*--*--*--*-");
        System.out.println("-*-                                                -*-");
        System.out.println("-*-Function Number:                                -*-");
        System.out.println("-*-   1: Feature Extraction(188D)                  -*-");
        System.out.println("-*-   2: Imbalanced data Process(KEEL)             -*-");
        System.out.println("-*-   3: Feature Selection(MRMD)                   -*-");
        System.out.println("-*-   4: Classifier(LibD3C)                        -*-");
        System.out.println("-*-   5: Exit ProteinPipe                          -*-");
        System.out.println("-*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*-");
    }

    public static void CaseTest(int i){
        switch (i){
            case 1:{
                // 188D Feature Extraction
                FeatureExtraction fe = new FeatureExtraction();
                while(fe.getSmile_fe() != 0){
                    fe.doFeatureExtract();
                }
                fe.setSmile_fe(1);
                break;
            }
            case 2:{
                // KEEL Imbalanced data Process
                try{
                    ImblearnProcessed imblearnProcessed = new ImblearnProcessed();
                    imblearnProcessed.doImblearn();
                } catch(Exception e){

                }
                break;
            }
            case 3:{
                // MRMD Feature Selection
                try{
                    FeatureSelection fs = new FeatureSelection();
                    while (fs.getSmile_fs() != 0){
                        fs.doFeatureSelection();
                    }
                }catch(Exception e){

                }
                break;
            }
            case 4:{
                // LibD3C classifier
                try{
                    Classifier c = new Classifier();
                    c.doClassify();
                }catch (Exception e){

                }
                break;
            }
            case 5:{
                System.out.println("-*--*--*-    Good Bye and Have a NICE Day    -*--*--*-");
                System.exit(0);
            }

        }
    }
}