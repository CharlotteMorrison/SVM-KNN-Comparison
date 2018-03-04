import java.io.File;
import java.util.Map;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.ARFFHandler;




public class KNN {

    //counters
    int correct = 0, wrong = 0;
    
    public KNN () throws Exception {

    }
    
    public void runKNN (int n) throws Exception{ //# of neighbors
    	correct = 0;
    	wrong = 0;
    	
        //Load training data set.
    	
        Dataset data = ARFFHandler.loadARFF(new File("src/files/train.arff"), 0 );

        //Construct a KNN classifier that uses n neighbors to make a decision.
         
        Classifier knn = new KNearestNeighbors(n);
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

        /*
        Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(knn, dataForClassification);
        for (Object o : pm.keySet())
            System.out.println(o + ": " + pm.get(o).getAccuracy());        
		*/

    }

}