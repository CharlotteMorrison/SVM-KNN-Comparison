import java.util.*;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;
import java.util.*;
import java.io.*;

/**
 * 1. average prediction accuracy and std. deviation of KNN and SVM.
 * 2. average number of false positives for SVM and KNN.
 * 
 */

public class Main {

	public static void main(String[] args) throws Exception {
	
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/output.csv"));
		FileSplitter data = new FileSplitter();
		KNN knn = new KNN();
		SVM svm = new SVM();

		writer.write("Test #,K Value,KNN Right, KNN Wrong, KNN False +, KNN Avg. Accuracy, SVM Right, SVM Wrong, SVM False +, SVM Accuracy\n" );
		for(int k = 5; k <= 20; k += 5) {
			int x = 0;
			while (x < 10) {
				data.split("src/files/wine.arff");
				knn.runKNN(k);
				svm.runSVM();					
				// write to a csv file.
				writer.write(x +"," + k + "," + knn.correct + "," + knn.wrong + "," + knn.falsePositives + "," + knn.avgAccuracy + "," 
				+ svm.correct + "," + svm.wrong + "," + svm.falsePositives + "," + svm.avgAccuracy + "\n");
				x++;
			}		
		}
		writer.close();
	}

}
