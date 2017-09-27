
public class PrintTree {
	
	public void printTree(Tree decision_tree, int max_no_of_feature_values) {
		
		if((decision_tree.leftChild == null && decision_tree.rightChild == null) || decision_tree.instanceCount == 0 || (decision_tree.dataSet[0].length == 1)){
			System.out.println(decision_tree.getObject());
			return;
		}
		
		int classZeroCounter = 0;
		int classOneCounter = 0;
		
		int total_instances = 0;
		
		while(total_instances != decision_tree.instanceCount) {
			if(decision_tree.dataSet[total_instances][decision_tree.dataSet[0].length - 1] == 1) classZeroCounter++;
			else classOneCounter++;
			
			total_instances++;
		}
		
		if(classZeroCounter == decision_tree.instanceCount) {
			decision_tree.object = 1;
			System.out.println(decision_tree.getObject());
			return;
		}
		else if(classOneCounter == decision_tree.instanceCount) {
			decision_tree.object = 0;
			System.out.println(decision_tree.getObject());
			return;
		}
		
		System.out.println();
		
		//System.out.println("Max No of Feature Values"+max_no_of_feature_values);
		
		for(int i = max_no_of_feature_values; i> decision_tree.dataSet[0].length - 1;i--) {
			System.out.print("| ");
		}
		System.out.print(decision_tree.leftChild.getCheckedFeatureValues()+"= 1:");
		
		printTree(decision_tree.leftChild, max_no_of_feature_values);
		
		for(int i = max_no_of_feature_values; i> decision_tree.dataSet[0].length - 1;i--) {
			System.out.print("| ");
		}
		System.out.print(decision_tree.rightChild.getCheckedFeatureValues()+"= 0:");
		printTree(decision_tree.rightChild, max_no_of_feature_values);
	}
}
