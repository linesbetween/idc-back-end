package org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess;

import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.ling.Datum;

import java.io.File;

public class ClassifierOperation {
    public static ColumnDataClassifier scanDataSet(ColumnDataClassifier cdc) { //scan and merge all train files, and re-train them
        try {
            String trainFilePath = Constant.DATAPATH.value + File.separator + "allTrainDataSet.txt";
            if (new File(trainFilePath).exists()) new File(trainFilePath).delete();
            File oldModel = new File(Constant.DATAPATH.value + File.separator + "model.txt");
            if (oldModel.exists()) oldModel.delete();
            FileTransfer.mergeDataSet(new File(Constant.DATAPATH.value + File.separator + "permanent"), trainFilePath);
            System.out.println(trainFilePath);
            System.out.println(cdc.toString());
            if (new File(trainFilePath).exists()) cdc.trainClassifier(trainFilePath);
//            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(oldModel));
//            cdc.serializeClassifier(os);
//            os.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return cdc;
    }
    public static String predictSentence(ColumnDataClassifier cdc, String s) {
        String formatString = FileTransfer.formatString(s);
        Datum datum = cdc.makeDatumFromLine(formatString);
        return cdc.classOf(datum);
    }
}
