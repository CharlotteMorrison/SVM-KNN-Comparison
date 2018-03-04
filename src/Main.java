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
		
	FileSplitter files = new FileSplitter("src/files/wine.arff");

	KNN knn = new KNN();
	


	}

}
