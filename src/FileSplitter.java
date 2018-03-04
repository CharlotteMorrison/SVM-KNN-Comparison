
import java.io.BufferedWriter;
import java.io.FileWriter;

import net.sf.javaml.core.Dataset;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class FileSplitter {
	
	Instances test = null;
	Instances train = null;
	
	public FileSplitter(String dataFile) {
		//String dataFile = "src/files/wine.arff";
		Instances data;
		try {
			data = (new DataSource(dataFile)).getDataSet();
			data.setClassIndex(0);
			data.randomize(new java.util.Random());
			int trainSize = (int) Math.round(data.numInstances() * 34 / 100); 
			int testSize = data.numInstances() - trainSize; 
			Instances train = new Instances(data, 0, trainSize); 
			Instances test = new Instances(data, 0, testSize); 			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/train.arff"));
			writer.write(train.toString());
			writer.flush();
			writer.close();
			
			writer = new BufferedWriter(new FileWriter("src/files/test.arff"));
			writer.write(test.toString());
			writer.flush();
			writer.close();	
				
		
		} catch (Exception e) {
			System.out.println("error");
		}
	}
}
