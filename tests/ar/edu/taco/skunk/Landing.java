package ar.edu.taco.skunk;


public class Landing {

	static Boolean isHeightPositive(float height){
		Boolean b = null;
		if (height > 0)
			b = Boolean.TRUE;

		if (height <= 0)
			b = Boolean.FALSE;

		return b;
	}


	/*@ requires true;
	  @ ensures true;
	  @ signals (Exception e) false;
	  @*/
	static boolean initLandingOK(float height){

		Boolean b = isHeightPositive(height);

		return b.booleanValue();
	}






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




/*@ requires i2 != 0 && i3 != 0;
  @ ensures \result == true;
  @ signals (RuntimeException e) false;
  @*/
	public static boolean testDivZero(int i1, int i2, int i3){
		i2 = i1/(i2/i3);
		return true;
	}


//	public static void main(String[] args) {
//        int i1_Integer_1 = 536903680;
//        int i2_Integer_2 = 1;
//        int i3_Integer_3 = 536903680;
//		System.out.println(i1_Integer_1/(i2_Integer_2/i3_Integer_3));
//		boolean b = testDivZero(i1_Integer_1, i2_Integer_2, i3_Integer_3);
//		System.out.println(b);
//	}



	/*@ requires i > 5;
	  @ ensures \result == true;
	  @ signals (RuntimeException e) false;
	  @*/
	public static boolean testArrayObject(int i){
		Landing[] l = new Landing[i];
		l[0] = new Landing();
		l[i] = new Landing();
		return l.length > 5;
	}










	//		public static void main(String[] args){
	//			boolean b = initLandingOK(Float.NaN);
	//			System.out.println(b);
	//		}

//			public static void main(String[] args) {
//		        int i1 = 913832521;
//		        int i2 = 913832521;
//		        int i3 = 1039974511;
//		        int i4 = 913832521;
//		        int i5 = 111625831;
//				int result = i1*i2*i3*i4*i5;
//				System.out.println(result);
//			}
//
	//public static void main(String[] args) {
	//	float i1 = 1f;
	//	float i2 = 0f;
	//	boolean b = Landing.testDivZero(i1, i2);
	//}


}
