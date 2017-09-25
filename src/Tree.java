
public class Tree {
	
	public Tree(int i) {
		this.nNumber = i;
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
	 * @return the uAttribute
	 */
	public String getuAttribute() {
		return uAttribute;
	}

	/**
	 * @param uAttribute the uAttribute to set
	 */
	public void setuAttribute(String uAttribute) {
		this.uAttribute = uAttribute;
	}

	/**
	 * @return the rCount
	 */
	public int getrCount() {
		return rCount;
	}

	/**
	 * @param rCount the rCount to set
	 */
	public void setrCount(int rCount) {
		this.rCount = rCount;
	}

	/**
	 * @return the nNumber
	 */
	public int getnNumber() {
		return nNumber;
	}

	/**
	 * @param nNumber the nNumber to set
	 */
	public void setnNumber(int nNumber) {
		this.nNumber = nNumber;
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
	public String uAttribute;
	public int rCount;
	public int nNumber;
	public int[][] dataSet;
	
	public Tree() {
		// TODO Auto-generated constructor stub
		this.leftChild = null;
		this.rightChild = null;
	}
	
	
}
