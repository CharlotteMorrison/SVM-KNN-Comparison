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
    float avgAccuracy = 0;	
    double falsePositives = 0;    
    
    public KNN () throws Exception {

    }
    
    public void runKNN (int n) throws Exception{ //# of neighbors
    	correct = 0;
    	wrong = 0;
        avgAccuracy = 0;	
        falsePositives = 0;    	
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

        Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(knn, dataForClassification);
        
        for (Object o : pm.keySet()) {

        	//weighted average based on the number in each class (1=59, 2=71, 3=48)
        	if(o.equals("1")) {
        		avgAccuracy += pm.get(o).getAccuracy()*59;
        		falsePositives = pm.get(o).fp;
        	}else if(o.equals(2)){
        		avgAccuracy += pm.get(o).getAccuracy()*71;
        		falsePositives = pm.get(o).fp;
        	}else {
        		avgAccuracy += pm.get(o).getAccuracy()*48;
        		falsePositives = pm.get(o).fp;
        	}
        }
        System.out.println(falsePositives);
        avgAccuracy /= 178;

    }

}