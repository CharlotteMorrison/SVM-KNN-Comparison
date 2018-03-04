import java.io.File;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.ARFFHandler;
import net.sf.javaml.tools.data.FileHandler;



public class KNN {

    //counters
    int correct = 0, wrong = 0;
    
    public KNN () throws Exception {

        //Load training data set.
    	
        Dataset data = ARFFHandler.loadARFF(new File("src/files/train.arff"), 0 );

        //Construct a KNN classifier that uses 5 neighbors to make a decision.
         
        Classifier knn = new KNearestNeighbors(5);
        knn.buildClassifier(data);

        //Load the data set for testing
        Dataset dataForClassification = ARFFHandler.loadARFF(new File("src/files/test.arff"), 0 );

        
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