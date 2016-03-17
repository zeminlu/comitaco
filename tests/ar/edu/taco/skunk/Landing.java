package ar.edu.taco.skunk;



public class Landing {

	
	/*@ requires true;
	  @ ensures true;
	  @ signals (Exception e) false;
	  @*/
	static boolean initLandingOK(float height){

		Boolean b = isHeightPositive(height);

		return b.booleanValue();
	}

	
	
	static Boolean isHeightPositive(float height){
		Boolean b = null;
		if (height > 0)
			b = Boolean.TRUE;

		if (height <= 0)
			b = Boolean.FALSE;

		return b;
	}







	
	
//	public static void main(String[] args) {
//	initLandingOK(Float.NaN);
//}


	
	
	
	
	
	
	
	/*@
	 @ requires i1 != 1 && i2 != 1 && i3 != 1 && i4 != 1 && i5 != 1;
	 @ ensures \result == false; 
	 @*/
	public boolean aerodynamic1 (int i1, int i2, int i3, int i4, int i5){

		if (i1*i2*i3*i4*i5 == 1)
			return true;
		else
			return false;

	}

	
	
	
	
	/*@
	  @ requires f1 > 0 && f2 > 0 && f1 < 10f && f2 < 10f;
	  @ ensures \result == f1;
	  @*/
	public float testAddition(float f1, float f2){
		return (f1 + f2) - f2;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
//	public static void main(String[] args) {
//		initLandingOK(Float.NaN);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	










	
	
			public static void main(String[] args) {
			       int i1 = -1936783991;
			        int i2 = -246174513;
			        int i3 = 932549551;
			        int i4 = -2084010553;
			        int i5 = 175850495;
		 		int result = i1*i2*i3*i4*i5;
				System.out.println("El resultado de la multiplicación i1*i2*i3*i4*i5 es "+result);
			}

	


}
