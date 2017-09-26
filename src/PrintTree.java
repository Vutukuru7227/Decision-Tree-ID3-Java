
public class PrintTree {
	
	public void printTree(Tree decision_tree, int max_no_of_feature_values) {
		
		if((decision_tree.leftChild == null && decision_tree.rightChild == null) || decision_tree.instanceCount == 0 || (decision_tree.dataSet[0].length == 1)){
			System.out.println(decision_tree.getObject());
		}
		
		int rpc = 0;
		int rqc = 0;
		
		int total_instances = 0;
		
		while(total_instances != decision_tree.instanceCount) {
			if(decision_tree.dataSet[total_instances][decision_tree.dataSet[0].length - 1] == 1) rpc++;
			else rqc++;
			
			total_instances++;
		}
		
		if(rpc == decision_tree.instanceCount) {
			decision_tree.object = 1;
			System.out.println(decision_tree.getObject());
			return;
		}
		else if(rqc == decision_tree.instanceCount) {
			decision_tree.object = 0;
			System.out.println(decision_tree.getObject());
			return;
		}
		
		System.out.println();
		
		for(int i = max_no_of_feature_values; i> decision_tree.dataSet[0].length - 1;i--) {
			System.out.print("| ");
		}
		System.out.print(decision_tree.leftChild.getCheckedFeatureValues()+"= 1:");
		printTree(decision_tree, max_no_of_feature_values);
		
		for(int i = max_no_of_feature_values; i> decision_tree.dataSet[0].length - 1;i--) {
			System.out.print("| ");
		}
		System.out.print(decision_tree.rightChild.getCheckedFeatureValues()+"= 0:");
		printTree(decision_tree, max_no_of_feature_values);
	}
}
