package testers;

import utilityClasses.Hyperoperator;

public class HyperoperatorTester {

	public static void main(String[] args) {
		for(int i = 0; i <= 10; i++){
			for(int j = 0; j <= 5; j++){
				for(int k = 0; k <= 5; k++){
					System.out.println("M: " + i + ", a: " + j + ", b: " + k + " = " + Hyperoperator.hyperoperate(i, j, k));
				}
			}
		}
	}

}
