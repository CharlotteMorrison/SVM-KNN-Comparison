import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import libsvm.LibSVM;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.ARFFHandler;



public class SVM {
	//counters
    int correct = 0, wrong = 0;
    float accuracy = 0;	
    
    
	public SVM(){
		
	}
	
	public void runSVM() throws FileNotFoundException {
		correct = 0;
		wrong = 0;
		
		//load training dataset
        Dataset data = ARFFHandler.loadARFF(new File("src/files/train.arff"), 0 );
        
        //Contruct a LibSVM classifier with default settings.
         
        Classifier svm = new LibSVM();
        svm.buildClassifier(data);

        //load the dataset for testing
        Dataset dataForClassification = ARFFHandler.loadARFF(new File("src/files/test.arff"), 0 );

        //Classify all instances and check with the correct class values 
        for (Instance inst : dataForClassification) {
            Object predictedClassValue = svm.classify(inst);
            Object realClassValue = inst.classValue();
            if (predictedClassValue.equals(realClassValue)) {
                correct++;
            } else {
                wrong++;
            }
        }
        /*
        Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(svm, dataForClassification);
        for (Object o : pm.keySet())
            System.out.println(o + ": " + pm.get(o).getAccuracy());
        */
	}
}
