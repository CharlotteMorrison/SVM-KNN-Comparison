import java.io.File;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;



public class KNN {

    public KNN (String trainingData, String testData, int classLabel, String splitter) throws Exception {

        //Load training data set.
    	
        Dataset data = FileHandler.loadDataset(new File(trainingData), classLabel, splitter);
        
        //Construct a KNN classifier that uses 5 neighbors to make a decision.
         
        Classifier knn = new KNearestNeighbors(5);
        knn.buildClassifier(data);

        //Load the data set for testing
        Dataset dataForClassification = FileHandler.loadDataset(new File(testData), classLabel, splitter);

        //counters
        int correct = 0, wrong = 0;
        
        //classify instances and then check for correct values
        for (Instance inst : dataForClassification) {
            Object predictedClassValue = knn.classify(inst);
            Object realClassValue = inst.classValue();
            if (predictedClassValue.equals(realClassValue))
                correct++;
            else
                wrong++;
        }
        System.out.println("Correct predictions  " + correct);
        System.out.println("Wrong predictions " + wrong);

    }

}