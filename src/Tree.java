
public class Tree {
	
	public Tree(int i) {
		this.nodeId = i;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	/**
	 * @return the leftChild
	 */
	public Tree getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(Tree leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public Tree getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(Tree rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * @return the object
	 */
	public int getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(int object) {
		this.object = object;
	}


	/**
	 * @return the dataSet
	 */
	public int[][] getDataSet() {
		return dataSet;
	}

	/**
	 * @param dataSet the dataSet to set
	 */
	public void setDataSet(int[][] dataSet) {
		this.dataSet = dataSet;
	}

	public Tree leftChild;
	public Tree rightChild;
	public int object;
	public int[][] dataSet;
	
	/**
	 * @return the checkedFeatureValues
	 */
	public String getCheckedFeatureValues() {
		return checkedFeatureValues;
	}

	/**
	 * @param checkedFeatureValues the checkedFeatureValues to set
	 */
	public void setCheckedFeatureValues(String checkedFeatureValues) {
		this.checkedFeatureValues = checkedFeatureValues;
	}

	/**
	 * @return the instanceCount
	 */
	public int getInstanceCount() {
		return instanceCount;
	}

	/**
	 * @param instanceCount the instanceCount to set
	 */
	public void setInstanceCount(int instanceCount) {
		this.instanceCount = instanceCount;
	}

	/**
	 * @return the nodeId
	 */
	public int getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String checkedFeatureValues;
	public int instanceCount;
	public int nodeId;

	
	public Tree() {
		// TODO Auto-generated constructor stub
		this.leftChild = null;
		this.rightChild = null;
	}
	
	
}
