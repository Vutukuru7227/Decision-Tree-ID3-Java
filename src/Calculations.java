import java.util.Hashtable;

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