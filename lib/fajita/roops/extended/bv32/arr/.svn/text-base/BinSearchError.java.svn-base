package roops.extended.bv32.arr;
import roops.util.RoopsArray; @roops.util.BenchmarkClass

public class BinSearchError {

       
	@roops.util.NrOfGoals(1)
	@roops.util.BenchmarkMethod static
       public void test_bin_search(int[] a, int key) {
         if (a!=null) {
           int ret_val = binarySearch(a,key);
         }
       }	

       public static int binarySearch(int[] a, int key) {
		int low = 0;
		int high = RoopsArray.getLength(a) - 1;
		int mid;
		int midVal ;
		
		while (low <= high)  {
			mid = (low + high) /2;

                        if (mid<0) {
                          {roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}
                        }

			midVal = a[mid];

			if (midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid - 1;
			else {
				return mid; // key found
			}
		}
		
		return -(low+1);
	}
}
/* end roops.extended.bv32.arr */
