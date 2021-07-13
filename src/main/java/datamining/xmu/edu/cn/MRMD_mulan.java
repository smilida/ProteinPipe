package datamining.xmu.edu.cn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import weka.classifiers.meta.Bagging;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SelectedTag;

public class MRMD_mulan {
    static String[] classAttr;

    static double[][] feaData;

    static String[][] labelData;

    static List<Map.Entry<String, Double>> mrmrList;

    static String inputFile;

    static String outoputFile;

    static Calendar calendar = Calendar.getInstance();

    static int minutes = calendar.get(12);

    static String arff = "out_" + String.valueOf(minutes) + "." + "arff";

    static int insNum = 0;

    static int feaNum = 0;

    static int labNum = 1;

    static int seleFeaNum = 0;

    static int disFunc = 1;

    static double bestRate = 0.0D;

    static double auc = 0.0D;

    static boolean isAuto = true;

    private static int optNum;

    private static String model = "rf";

    static ArrayList<String> arrayList;

    static int smile_mrmd = 1;

    public static void main(String[] args) throws Exception {
        int i;
        smile_mrmd = 0;
        BasicParser basicParser = new BasicParser();
        Options options = new Options();
        options.addOption("h", "help", false, "Print this usage information");
        options.addOption("i", "input", true, "input file");
        options.addOption("o", "output", true, "output file  score rank of each feature");
        options.addOption("df", "disFuc", true, "the distance function default(1)");
        options.addOption("a", "arff", true, "outputfile of arff default (opt.arff)");
        options.addOption("m", "model", true, "opt model type defauly(rf)");
        options.addOption("N", "No", false, "auto select");
        options.addOption("sn", "sn", true, "select feature number");
        CommandLine commandLine = basicParser.parse(options, args);
        String file = "";
        if (commandLine.hasOption('N'))
            isAuto = false;
        if (commandLine.hasOption("sn"))
            seleFeaNum = Integer.parseInt(commandLine.getOptionValue("sn"));
        if (commandLine.hasOption('h')) {
            print_help();
            smile_mrmd = 1;
            //System.exit(0);
        }
        if (!commandLine.hasOption('i') || !commandLine.hasOption('o')) {
            smile_mrmd = 1;
            System.out.println("You should input -i , -o ");
            //System.exit(0);
        }
        outoputFile = commandLine.getOptionValue('o');
        inputFile = commandLine.getOptionValue('i');
        if (commandLine.hasOption("a"))
            arff = commandLine.getOptionValue("a");
        if (commandLine.hasOption("m")) {
            model = commandLine.getOptionValue("m");
            if (!model.equals("rf") && !model.equals("svm") && !model.equals("bagging") && !model.equals("N")) {
                System.out.println("model only can be rf,svm,bagging,N\n");
                smile_mrmd = 1;
                //System.exit(0);
            }
        }
        if (commandLine.hasOption("df")) {
            disFunc = Integer.parseInt(commandLine.getOptionValue("df"));
            if (disFunc < 1 || disFunc > 4)
                System.out.println("\r\nThe parameter of -df is error !! df={1, 2, 3, 4}\r\n");
        }
        File InputFile = new File(inputFile);
        if (!InputFile.exists()) {
            System.out.println("Can't find input file: " + InputFile);
            smile_mrmd = 1;
            //System.exit(0);
        }
        BufferedReader InputBR = new BufferedReader(new InputStreamReader(new FileInputStream(InputFile), "utf-8"));
        String InputLine = InputBR.readLine();
        while (!InputLine.toUpperCase().contains("@DATA"))
            InputLine = InputBR.readLine();
        InputLine = InputBR.readLine();
        while (InputLine != null) {
            if (InputLine.equals("")) {
                InputLine = InputBR.readLine();
                continue;
            }
            if (insNum == 1)
                feaNum = (InputLine.split(",")).length;
            insNum++;
            InputLine = InputBR.readLine();
        }
        InputBR.close();
        feaNum -= labNum;
        if (seleFeaNum == 0)
            seleFeaNum = feaNum;
        String[][] inputData = new String[insNum][feaNum + labNum];
        getData gd = new getData();
        gd.setData(inputData);
        gd.setFeaNum(feaNum);
        gd.setInsNum(labNum);
        gd.setLabNum(labNum);
        gd.run(inputFile);
        double[] PearsonValue = new double[feaNum];
        Pearson pd = new Pearson(feaNum, labNum);
        pd.setInsNum(insNum);
        pd.setFeaNum(feaNum);
        pd.setLabNum(labNum);
        pd.setData(inputData);
        pd.setPearsonValue(PearsonValue);
        pd.run();
        labelData = new String[insNum][labNum];
        getLabel gl = new getLabel();
        gl.setData(labelData);
        gl.setFeaNum(feaNum);
        gl.setInsNum(insNum);
        gl.setLabNum(labNum);
        gl.run(inputData);
        feaData = new double[insNum][feaNum];
        getFeaData gfd = new getFeaData();
        gfd.setData(feaData);
        gfd.setFeaNum(feaNum);
        gfd.setInsNum(insNum);
        gfd.setLabNum(labNum);
        gfd.run(inputData);
        inputData = (String[][])null;
        double[] CosineValue = new double[feaNum];
        Cosine cd = new Cosine(feaNum);
        cd.setCosineValue(CosineValue);
        cd.setData(feaData);
        cd.setFeaNum(feaNum);
        cd.setInsNum(insNum);
        cd.setLabNum(labNum);
        double[] EuclideanValue = new double[feaNum];
        Euclidean ed = new Euclidean(feaNum);
        ed.setData(feaData);
        ed.setEuclideanValue(EuclideanValue);
        ed.setFeaNum(feaNum);
        ed.setInsNum(insNum);
        double[] TanimotoValue = new double[feaNum];
        Tanimoto td = new Tanimoto(feaNum);
        td.setData(feaData);
        td.setTanimotoValue(TanimotoValue);
        td.setFeaNum(feaNum);
        td.setInsNum(insNum);
        double[] mrmrValue = new double[feaNum];
        switch (disFunc) {
            case 1:
                ed.run();
                for (i = 0; i < feaNum; i++)
                    mrmrValue[i] = EuclideanValue[i] + PearsonValue[i];
                break;
            case 2:
                cd.run();
                for (i = 0; i < feaNum; i++)
                    mrmrValue[i] = CosineValue[i] + PearsonValue[i];
                break;
            case 3:
                td.run();
                for (i = 0; i < feaNum; i++)
                    mrmrValue[i] = TanimotoValue[i] + PearsonValue[i];
                break;
            case 4:
                ed.run();
                cd.run();
                td.run();
                for (i = 0; i < feaNum; i++)
                    mrmrValue[i] = (PearsonValue[i] * 3.0D + EuclideanValue[i] + CosineValue[i] + TanimotoValue[i]) / 3.0D;
                break;
        }
        Instances data = new Instances(new BufferedReader(new FileReader(inputFile)));
        data.setClassIndex(data.numAttributes() - 1);
        Random rand = new Random(1L);
        Instances randData = new Instances(data);
        randData.randomize(rand);
        Enumeration data_e = data.enumerateAttributes();
        arrayList = new ArrayList<>(21);
        int index = 0;
        while (data_e.hasMoreElements()) {
            String str = data_e.nextElement().toString();
            str = str.split(" ")[1];
            arrayList.add(str);
        }
        double max = 0.0D;
        int j;
        for (j = 0; j < mrmrValue.length; j++) {
            if (mrmrValue[j] > max)
                max = mrmrValue[j];
        }
        for (j = 0; j < mrmrValue.length; j++)
            mrmrValue[j] = mrmrValue[j] / max;
        Map<String, Double> mrmrMap = new HashMap<>();
        mrmrList = new ArrayList<>(mrmrMap.entrySet());
        mrmrList = initialHashMap(mrmrValue, feaNum);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outoputFile), false), "utf-8"));
        bufferedWriter.write("The number of selected features is: " + seleFeaNum + "\r\n\r\n");
        bufferedWriter.write("Num\t\tFeaName\t\tScore\r\n\r\n");
        int line = 1;
        for (int k = 0; k < seleFeaNum; k++) {
            bufferedWriter.write(line + "\t\t" + (String)((Map.Entry)mrmrList.get(k)).getKey() + "\t\t" + ((Map.Entry)mrmrList.get(k)).getValue() + "\r\n");
            line++;
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        classAttr = new String[labNum];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputFile)), "utf-8"));
        String lineString = bufferedReader.readLine();
        while (!lineString.contains("@relation") && !lineString.contains("@RELATION") && !lineString.contains("@Relation"))
            lineString = bufferedReader.readLine();
        lineString = bufferedReader.readLine();
        while (lineString.length() == 0)
            lineString = bufferedReader.readLine();
        int count = 0;
        while (count < feaNum) {
            if (lineString.length() != 0) {
                count++;
                lineString = bufferedReader.readLine();
                continue;
            }
            lineString = bufferedReader.readLine();
        }
        while (lineString.length() == 0)
            lineString = bufferedReader.readLine();
        count = 0;
        while (count < labNum) {
            if (lineString.length() != 0) {
                classAttr[count] = lineString;
                count++;
                lineString = bufferedReader.readLine();
                continue;
            }
            lineString = bufferedReader.readLine();
        }
        bufferedReader.close();
        System.out.println("MRMD over.");
        System.out.println("Feature selction optimation begin");
        System.out.println("model:" + model);
        if (isAuto) {
            int num = optSelect();
            writeFeature(num);
            System.out.println("The best feature number: " + String.valueOf(optNum));
            System.out.println("The best rate: " + String.valueOf(bestRate));
            System.out.println("Feature selction optimation end,the best arff save " + arff);
        }
    }

    public static void print_help() {
        System.out.println("Usage: -i inputFile -o outputFile \n-df disFunc default(1) \n-a arff default(opt.arff) \n-model rf default(rf)");
        System.out.println("[-i -o] is Necessary   \n[-sn -ln -df -a - model] is Optional ");
        smile_mrmd = 1;
        // System.exit(0);
    }

    public static int optSelect() throws Exception {
        double max = 0.0D;
        optNum = 1;
        double temp = 0.0D;
        for (int i = 1; i <= seleFeaNum; i++) {
            try {
                writeFeature(i);
                temp = featureRate();
                System.out.println("feature num: " + i + " rate: " + temp);
                if (temp > max) {
                    max = temp;
                    optNum = i;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bestRate = max;
        writeFeature(optNum);
        return optNum;
    }

    public static void writeFeature(int seleFeaNum) throws IOException {
        BufferedWriter arffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(arff), false), "utf-8"));
        arffWriter.write("@relation " + (new File(inputFile)).getName() + "_feaSele");
        arffWriter.newLine();
        arffWriter.newLine();
        int i;
        for (i = 0; i < seleFeaNum; i++) {
            arffWriter.write("@attribute " + (String)((Map.Entry)mrmrList.get(i)).getKey() + " numeric");
            arffWriter.newLine();
        }
        for (i = 0; i < labNum; i++) {
            arffWriter.write(classAttr[i]);
            arffWriter.newLine();
        }
        arffWriter.write("\r\n@data\r\n\r\n");
        for (i = 0; i < insNum; i++) {
            int j;
            for (j = 0; j < seleFeaNum; j++) {
                int index = arrayList.indexOf(((Map.Entry)mrmrList.get(j)).getKey());
                arffWriter.write(feaData[i][index] + ",");
            }
            for (j = 0; j < labNum - 1; j++)
                arffWriter.write(labelData[i][j] + ",");
            arffWriter.write(labelData[i][labNum - 1]);
            arffWriter.newLine();
        }
        arffWriter.flush();
        arffWriter.close();
    }

    public static Classifier getClassifier(String name) throws Exception {
        RandomForest randomForest;
        LibSVM libSVM;
        Bagging bagging;
        String[] options;
        Classifier classify = null;
        int enum_type = 1;
        if (name.equals("rf"))
            enum_type = 1;
        if (name.equals("svm"))
            enum_type = 2;
        if (name.equals("bagging"))
            enum_type = 3;
        switch (enum_type) {
            case 1:
                randomForest = new RandomForest();
                options = new String[] { "-I 100" };
                randomForest.setMaxDepth(0);
                randomForest.setNumTrees(100);
                randomForest.setNumFeatures(0);
                randomForest.setSeed(1);
                return (Classifier)randomForest;
            case 2:
                libSVM = new LibSVM();
                libSVM.setCacheSize(40.0D);
                libSVM.setCoef0(0.0D);
                libSVM.setCost(1.0D);
                libSVM.setDegree(3);
                libSVM.setEps(0.001D);
                libSVM.setGamma(0.0D);
                libSVM.setKernelType(new SelectedTag(2, LibSVM.TAGS_KERNELTYPE));
                libSVM.setDoNotReplaceMissingValues(false);
                libSVM.setLoss(0.1D);
                libSVM.setNormalize(false);
                libSVM.setNu(0.5D);
                libSVM.setProbabilityEstimates(false);
                libSVM.setShrinking(true);
                libSVM.setSVMType(new SelectedTag(0, LibSVM.TAGS_SVMTYPE));
                return (Classifier)libSVM;
            case 3:
                bagging = new Bagging();
                bagging.setBagSizePercent(100);
                bagging.setCalcOutOfBag(false);
                bagging.setDebug(false);
                bagging.setNumExecutionSlots(1);
                bagging.setNumIterations(10);
                bagging.setSeed(1);
                return (Classifier)bagging;
        }
        System.out.println("?????????");
        return (Classifier)classify;
    }

    public static double featureRate() throws Exception {
        FileReader reader = new FileReader(arff);
        Instances dataset = new Instances(reader);
        dataset.setClassIndex(dataset.numAttributes() - 1);
        Classifier classify = getClassifier(model);
        int seed = 1;
        int folds = 10;
        Random rand = new Random(seed);
        Instances randData = new Instances(dataset);
        randData.randomize(rand);
        Evaluation evalAll = new Evaluation(randData);
        if (randData.classAttribute().isNominal())
            randData.stratify(folds);
        if (insNum < 10)
            folds = insNum;
        for (int n = 0; n < folds; n++) {
            Evaluation eval = new Evaluation(randData);
            Instances train = randData.trainCV(folds, n);
            Instances test = randData.testCV(folds, n);
            classify.buildClassifier(train);
            eval.evaluateModel(classify, test, new Object[0]);
            evalAll.evaluateModel(classify, test, new Object[0]);
        }
        return evalAll.pctCorrect();
    }

    public static List initialHashMap(double[] data, int feaNum) {
        Map<String, Double> mrmrMap = new HashMap<>();
        for (int i = 0; i < feaNum; i++) {
            String str = arrayList.get(i);
            mrmrMap.put(str, Double.valueOf(data[i]));
        }
        List<Map.Entry<String, Double>> mrmrList = new ArrayList<>(mrmrMap.entrySet());
        Collections.sort(mrmrList, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if (Double.parseDouble(((Double)o1.getValue()).toString()) < Double.parseDouble(((Double)o2.getValue()).toString()))
                    return 1;
                if (Double.parseDouble(((Double)o1.getValue()).toString()) == Double.parseDouble(((Double)o2.getValue()).toString()))
                    return 0;
                return -1;
            }
        });
        return mrmrList;
    }

    public static String getArff(){
        return arff;
    }

    public static int getSmile_mrmd() {
        return smile_mrmd;
    }
}
