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
		int leftChildElementCounter = 0;
		int rightChildElementCounter = 0;
		int leftChildRow = 0;
		int leftChildColumn = 0;
		int rightChildRow = 0;
		int rightChildColumn = 0;

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
		System.out.println();
		//System.out.println("Base Entropy="+base_entropy);
		//TODO: Identify the best attribute to split based on the information gain
		for(int i=0; i < no_of_feature_values-1 ;i++) {
			double information_gain = calculate.informationGain(no_of_instances, decision_tree.dataSet, i, no_of_feature_values-1, base_entropy);
			//System.out.println("Information Gain for :"+feature_values[i]+" = "+information_gain);
			if(information_gain > highest_information_gain) {
				highest_information_gain = information_gain;
				feature_values_to_be_used = i;
			}
		}
		
		//TODO: Get the number of elements on both childs individually
		for(int i=0;i<no_of_instances;i++) {
			if(decision_tree.dataSet[i][feature_values_to_be_used] == 0) rightChildElementCounter++;
			else leftChildElementCounter++;
		}
		
		decision_tree.instanceCount = no_of_instances;
		
		//TODO: Construct the left and right tree from the above details
		decision_tree.leftChild = new Tree(++count);
		//decision_tree.leftChild.dataSet = decision_tree.dataSet[no_of_instances][feature_values_to_be_used];
		decision_tree.leftChild.dataSet = new int[leftChildElementCounter][no_of_feature_values-1];
		
		decision_tree.rightChild = new Tree(++count);
		decision_tree.rightChild.dataSet = new int[rightChildElementCounter][no_of_feature_values-1];
		
		//TODO: Filling the left tree components and right tree components
		for(int i=0;i<no_of_instances;i++) {
			if(decision_tree.dataSet[i][feature_values_to_be_used] == 1) {
				for(int j=0;j<no_of_feature_values;j++) {
					if(j == feature_values_to_be_used) continue;
					else {
						decision_tree.leftChild.dataSet[leftChildRow][leftChildColumn] = decision_tree.dataSet[i][j];
						leftChildColumn++;
					}
				}
				leftChildRow++;
				leftChildColumn = 0;
			}else {
				for(int j =0;j<no_of_feature_values;j++) {
					if(j == feature_values_to_be_used) continue;
					else {
						decision_tree.rightChild.dataSet[rightChildRow][rightChildColumn] = decision_tree.dataSet[i][j];
						rightChildColumn++;
					}
				}
				rightChildRow++;
				rightChildColumn = 0;
			}
		}
		
//		System.out.println("Printing left child values");
//		for(int i=0;i<leftChildRow;i++) {
//			for(int j=0;j<no_of_feature_values-1;j++) {
//				System.out.print(decision_tree.leftChild.dataSet[i][j]+"\t");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("============================================");
//		
//		System.out.println("Printing right child values");
//		for(int i=0;i<rightChildRow;i++) {
//			for(int j=0;j<no_of_feature_values-1;j++) {
//				System.out.print(decision_tree.rightChild.dataSet[i][j]+"\t");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("============================================");
//		
//		System.out.println("Feature to be used:"+feature_values[feature_values_to_be_used]);
//		System.out.println("PRINT DUMMY");
		
		
		String[] remaining_attributes = getNewFeatureValueArray(feature_values, feature_values_to_be_used);
		
		//Building the left tree recursively
		decision_tree.leftChild.setCheckedFeatureValues(feature_values[feature_values_to_be_used]);

		for(int count = max_no_of_feature_values; count>no_of_feature_values;count--) System.out.print("| ");
		System.out.print(decision_tree.leftChild.getCheckedFeatureValues()+"= 1 :");
		decision_tree.leftChild.instanceCount = leftChildElementCounter;
		treeRecursion(decision_tree.leftChild, leftChildElementCounter, no_of_feature_values - 1, calculate.calculatePartialSetEntropy(decision_tree.dataSet, no_of_instances, 1, feature_values_to_be_used, no_of_feature_values - 1), remaining_attributes);
		
		//Building the right tree recursively
		decision_tree.rightChild.setCheckedFeatureValues(feature_values[feature_values_to_be_used]);
		for(int count = max_no_of_feature_values; count>no_of_feature_values;count--) System.out.print("| ");
		System.out.print(decision_tree.rightChild.getCheckedFeatureValues()+"= 0 :");
		decision_tree.rightChild.instanceCount = rightChildElementCounter;
		treeRecursion(decision_tree.rightChild, rightChildElementCounter, no_of_feature_values - 1, calculate.calculatePartialSetEntropy(decision_tree.dataSet, no_of_instances, 0, feature_values_to_be_used, no_of_feature_values - 1), remaining_attributes);
		
		
		
	}
	
	public static String[] getNewFeatureValueArray(String[] Feature_Values, int feature_values_to_be_used) {
		String newFeatureValueStringArray[] = new String[Feature_Values.length-1];
		for(int i=0,j=0; i< Feature_Values.length;i++) {
			if(feature_values_to_be_used == i) continue;
			else {
				newFeatureValueStringArray[j] = Feature_Values[i];
				j++;
			}
		}
		
		return newFeatureValueStringArray;
	}
	
	
	
	public static void main(String[] args) {
		
		DecisionTree dt = new DecisionTree();
		Tree decision_tree = new Tree();
		Tree prunedDecisionTree = new Tree();
		double pruning_factor = Double.parseDouble(args[3]);
		
		
		System.out.println("Pre-Pruned Accuracy");
		System.out.println("-----------------------------------");
		//decision_tree = dt.buildTree("./data_sets1/training_set.csv");
		Accuracy accuracy = new Accuracy();
		decision_tree = dt.buildTree(args[0]);
		
		System.out.println("Number of training instances = "+ decision_tree.instanceCount);
		System.out.println("Number of training attributes = "+ max_no_of_feature_values);
		accuracy.calculateAccuracyHeuristics(decision_tree, args[0], " training", "Before Pruning");
		System.out.println();
		
		accuracy.calculateAccuracyHeuristics(decision_tree, args[1], " validation", "Before Pruning");
		System.out.println();
		
		accuracy.calculateAccuracyHeuristics(decision_tree, args[2], " test", "Before Pruning");		
		System.out.println();
		
		System.out.println("==============================================");
		
		
		System.out.println("Post-Pruning Accuracy");
		System.out.println("---------------------------------");
		
		Pruning prune = new Pruning();
		PrintTree print = new PrintTree();
		
		prunedDecisionTree = prune.pruneTree(decision_tree, pruning_factor);
		print.printTree(prunedDecisionTree, max_no_of_feature_values);
		//System.out.println("Total Number of nodes: "+ prunedDecisionTree.totalNumNodes(prunedDecisionTree));
		accuracy.calculateAccuracyHeuristics(prunedDecisionTree, args[0], " Training Set", "After Pruning");
		System.out.println();
		
		accuracy.calculateAccuracyHeuristics(prunedDecisionTree, args[1], " Validation Set", "After Pruning");
		System.out.println();
		
		accuracy.calculateAccuracyHeuristics(prunedDecisionTree, args[2], " Testing Set", "After Pruning");
		
	}
}
