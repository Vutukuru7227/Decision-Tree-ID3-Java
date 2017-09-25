
public class Calculations {
	/*
	 *  -p/(p+n) log (p/p+n) - (n/p+n) log(n/p+n)
	 * 
	 */
	public double calculateEntropy(int[] dataSet) {
		//Hashtable<Integer, Integer> countTable = new Hashtable<>();
		int oneCounter = 0;
		int zeroCounter = 0;
		
		for(int i : dataSet) {
			if(i == 0) zeroCounter++;
			else oneCounter++;
		}
		
		double pValue = oneCounter/(double)dataSet.length;
		
		double nValue = zeroCounter/(double)dataSet.length;
		
		if(pValue == 0) pValue = 1;
		else if (nValue == 0) nValue = 1;
		
		double entropy = ((-pValue) * (logBase2(pValue))) + ((-nValue) * (logBase2(nValue)));
		
		return entropy;
	}
	
	public double splitDataEntropy(int no_of_instances, int label, int[][] data_set, int feature_value_num, int class_label) {
		int[] class_label_data = new int[no_of_instances];
		int satisfying_instances = 0;
		int total_instances = 0;
		
		for(int i=0, j=0;i<no_of_instances;i++) {
			if(data_set[i][feature_value_num] == label) {
				class_label_data[j] = data_set[i][class_label];
				j++;
				satisfying_instances++;
				total_instances++;
			}
		}
		
		double result = ((satisfying_instances/(double)total_instances) * calculateEntropy(class_label_data));
		
		return result;
	}
	
	
	public double informationGain(int no_of_instances,int[][] data_set,int feature_value_num,int class_label, double base_entropy) {
		double total_entropy = splitDataEntropy(no_of_instances, 0, data_set, feature_value_num, class_label) - splitDataEntropy(no_of_instances, 1, data_set, feature_value_num, class_label);
		
		double result = total_entropy - base_entropy;
		
		return result;
	}
	
	
	
	public double logBase2(double pnValue) {
		return (Math.log(pnValue)/Math.log(2));
	}
	
	public static void main(String[] ar) {
		int[] dataSet = {1,1,1,1,0,0,0,0};
		
		Calculations calc = new Calculations();
		double entropy = calc.calculateEntropy(dataSet);
		System.out.println(entropy);
	}
}
