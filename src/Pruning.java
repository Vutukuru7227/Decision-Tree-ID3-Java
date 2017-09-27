import java.util.Random;

public class Pruning {

	public Tree pruneTree(Tree decision_tree, double pruning_factor) {
		Tree temp_decision_tree = new Tree();
		Tree check = new Tree();
		
		System.out.println("Total Number of nodes in the tree sent in the argument"+decision_tree.totalNumNodes(decision_tree));
		temp_decision_tree = decision_tree;
		
		int numOfNodesToPrune = (int) (totalNodesInATree(decision_tree) * pruning_factor);
		int[] pruneList = pruneList(numOfNodesToPrune , totalNodesInATree(decision_tree));
		System.out.println("Length = "+numOfNodesToPrune);
		
		for(int i=0;i<pruneList.length;i++) {
			System.out.println("I = "+i+"; Value = "+pruneList[i]);
			temp_decision_tree = check.search(decision_tree, pruneList[i]);
			
			if(temp_decision_tree == null) continue;
			if(temp_decision_tree == decision_tree) continue;
			if(temp_decision_tree.leftChild == null || temp_decision_tree.rightChild == null) {
				System.out.println("I am sending it away");
				continue;
			}
			
			temp_decision_tree.leftChild = null;
			temp_decision_tree.rightChild = null;
			
			int classOneCounter = 0;
			int classZeroCounter = 0;
			
			for(int j=0;j<temp_decision_tree.instanceCount;j++) {
				if(temp_decision_tree.dataSet[j][temp_decision_tree.dataSet[j].length - 1] == 1) classOneCounter++;
				else classZeroCounter++;
			}
			
			if(classOneCounter >= classZeroCounter)temp_decision_tree.object = 1;
			else temp_decision_tree.object = 0;
			
		}
//		int counter = 0;
//		while(numOfNodesToPrune > 0 || counter == pruneList.length) {
//			temp_decision_tree = temp_decision_tree.search(decision_tree, pruneList[counter]);
//			
//			if(temp_decision_tree == null) {
//				numOfNodesToPrune--;
//				continue;
//			}
//			if(temp_decision_tree == decision_tree) {
//				System.out.println("I am sending it away");
//				continue;
//			}
//			if(temp_decision_tree.leftChild == null && temp_decision_tree.rightChild == null) {
//				System.out.println("I am sending it away too");
//				break;
//			}
//			
//			temp_decision_tree.leftChild = null;
//			temp_decision_tree.rightChild = null;
//			
//			int classOneCounter = 0;
//			int classZeroCounter = 0;
//			
//			for(int j=0;j<temp_decision_tree.instanceCount;j++) {
//				if(temp_decision_tree.dataSet[j][temp_decision_tree.dataSet[j].length - 1] == 1) classOneCounter++;
//				else classZeroCounter++;
//			}
//			System.out.println("Zero counter:"+classZeroCounter);
//			System.out.println("One counter:"+classOneCounter);
//			
//			if(classOneCounter >= classZeroCounter) temp_decision_tree.object = 1;
//			else temp_decision_tree.object = 0;
//			
//			counter++;
//			System.out.println("Counter value incleased to"+	counter);
//			numOfNodesToPrune--;
//		}
		System.out.println("==>"+temp_decision_tree.totalNumNodes(temp_decision_tree));
		return decision_tree;
	}
	
	public int totalNodesInATree(Tree tree) {
		
		return tree.totalNumNodes(tree);
	}
	
	public int[] pruneList(int pruneCount,int totalNumOfNodes) {
		int[] pruneList = new int[pruneCount];
		Random random = new Random();
		for(int i=0;i<pruneList.length;i++) {
			int id = random.nextInt(totalNumOfNodes - 1)+1; //To avoid selecting the root element
			pruneList[i] = id;
		}
		return pruneList;
	}
}
