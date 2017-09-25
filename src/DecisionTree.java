import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class DecisionTree {

	public static int max_no_of_feature_values;
	public static int count = 0;
	
	public Tree buildTree(String arg) {
		String[] feature_values;
		int no_of_feature_values = 0;
		int[][] data_set;
		int no_of_instances = 0;
		
		Tree decisionTree = new Tree(++count);
		File training_set = new File(arg);
		try {
			Scanner sc = new Scanner(training_set);
			
			//TODO: Find the number of feature values
			feature_values = sc.nextLine().split(",");
			
			//TOD): Find the feature value count
			no_of_feature_values = feature_values.length;
			max_no_of_feature_values = feature_values.length;
			
			//TODO: Find the number of instances in the data set
			while(sc.hasNextLine()) {
				sc.nextLine();
				no_of_instances++;
			}
			
			//TODO: Fill in the instances
			sc.close();
			data_set = fillDataSet(training_set, no_of_instances, no_of_feature_values);
			
			/*
			 * Uncomment the below code to see the csv file components
			 * 
			 * 
			 * for(int i=0;i<no_of_feature_values;i++) {
			 *	System.out.print(feature_values[i]+"\t");			
			 *	}
			 *	System.out.println();
			 *	for(int i=0;i<no_of_instances;i++) {
			 *		for(int j=0;j<no_of_feature_values;j++) {
			 *			System.out.print(data_set[i][j]+"\t");
			 *		}
			 *		System.out.println();
			 *	}
			 * 
			 */
			
			int[] class_labels = fillClassLabels(data_set, no_of_instances, no_of_feature_values);
			
			//for(int i=0;i<class_labels.length;i++) {
			//System.out.println(class_labels[i]);
			//}
			
			//TODO: Calculate base entropy
			Calculations calculate = new Calculations();
			double base_entropy = calculate.calculateEntropy(class_labels);
			// System.out.println(base_entropy);
			
			decisionTree.setDataSet(data_set);
			treeRecursion(decisionTree, no_of_instances, no_of_feature_values, base_entropy, feature_values);
			return decisionTree;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return decisionTree;
	}
	
	public static int[][] fillDataSet(File data,int no_of_instances,int no_of_feature_values) {
		int[][] data_set = new int[no_of_instances][no_of_feature_values];

		try { 
			Scanner sc = new Scanner(data);
			sc.nextLine();
			for(int i=0;sc.hasNextLine();i++) {
				String str = sc.nextLine();
				for(int j=0;j<no_of_feature_values;j++) {
					data_set[i][j] = Integer.parseInt(str.split(",")[j]);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data_set;
	}
	
	public static int[] fillClassLabels(int[][] data_set, int no_of_instances, int no_of_feature_labels) {
		int[] class_labels = new int[no_of_instances];
		
		for(int i=0;i<no_of_instances;i++) {
			class_labels[i] = data_set[i][no_of_feature_labels - 1];
		}
		return class_labels;
	}
	
	public static void treeRecursion(Tree decision_tree,int no_of_instances,int no_of_feature_values,double base_entropy,String[] feature_values) {
		double highest_information_gain = 0.0;
		Calculations calculate = new Calculations();
		int feature_values_to_be_used = 0;

		//TODO: Handle case where there are no instances to classify
		if(no_of_instances == 0) {
			Random random = new Random();
			decision_tree.setObject(random.nextInt(2));
			System.out.println(decision_tree.getObject());
			return;
		}
		
		//TODO: Handle scenario where there is only one feature value left
		if(no_of_feature_values == 1) {
			int zeroCounter = 0;
			int oneCounter = 0;
			
			for(int i=0;i<no_of_instances;i++) {
				if(decision_tree.dataSet[i][0] == 0) zeroCounter++;
				else oneCounter++;
			}
			
			if(zeroCounter > oneCounter) decision_tree.object = 0;
			else decision_tree.object = 1;
			System.out.println(decision_tree.getObject());
			return;
		}
		
		//TODO: Get the number of class label of each set(values) 
		int class_one_count = 0;
		int class_zero_count = 0;
		
		for(int i=0;i<no_of_instances;i++) {
			if(decision_tree.dataSet[i][no_of_feature_values-1] == 1) class_one_count++;
			else class_zero_count++;
		}
		if(class_one_count == no_of_instances) {
			decision_tree.object = 1;
			System.out.println(decision_tree.getObject());
			return;
		}else if(class_zero_count == no_of_instances){
			decision_tree.object = 0;
			System.out.println(decision_tree.getObject());
			return;
		}
		
		
		for(int i=0; i < no_of_feature_values-1 ;i++) {
			double information_gain = calculate.informationGain(no_of_instances, decision_tree.dataSet, i, no_of_feature_values-1, base_entropy);
			if(information_gain > highest_information_gain) {
				highest_information_gain = information_gain;
				feature_values_to_be_used = i;
			}
		}
		
	}
	
	public static void main(String[] args) {
		DecisionTree dt = new DecisionTree();
		dt.buildTree("./data_sets1/training_set.csv");
	}
}
