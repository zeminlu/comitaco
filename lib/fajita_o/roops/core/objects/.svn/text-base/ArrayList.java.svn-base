package roops.core.objects;

/**
 * 
 * @author Apache Harmony software http://harmony.apache.org/
 *         http://www.docjar.com/html/api/java/util/ArrayList.java.html
 * 
 */
public class ArrayList {

	/**
	 * @Modifies_Everything;
	 * 
	 * @Ensures false;
	 */
	static public void containsTest(/*@ nullable @*/ ArrayList arrayList, /*@ nullable @*/ Object o) {
		if (arrayList != null) {
			boolean ret_val = arrayList.contains(o);
		} else {
			/*{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}*/
		}
	}
	
	/**
	 * @Modifies_Everything;
	 * 
	 * @Ensures false;
	 */
    static public void removeTest(/*@ nullable @*/ ArrayList arrayList, int index) {
		if (arrayList != null) {
			/*{roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}*/
			Object ret_val = arrayList.remove(index);
		} else {
			/*{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}*/
		}
	}

	/**
	 * @Modifies_Everything;
	 * 
	 * @Ensures false;
	 */
	static public void addTest(/*@ nullable @*/ ArrayList arrayList, /*@ nullable @*/ Object o) {
		if (arrayList != null) {
			/*{roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}*/
			boolean ret_val = arrayList.add(o);
		} else {
			/*{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}*/
		}
	}

	private int firstIndex;

	private int lastIndex;

	private Object[] array;

	private int modCount;

	public boolean contains(Object object) {

		if (object != null) {
			/*{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}*/
			for (int i = firstIndex; i < lastIndex; i++) {
				/*{roops.util.Goals.reached(4, roops.util.Verdict.REACHABLE);}*/
				if (object_equals(object, array[i])) {
					/*{roops.util.Goals.reached(6, roops.util.Verdict.REACHABLE);}*/
					return true;
				} else {
					/*{roops.util.Goals.reached(7, roops.util.Verdict.REACHABLE);}*/
				}
			}
			/*{roops.util.Goals.reached(5, roops.util.Verdict.REACHABLE);}*/
		} else {
			/*{roops.util.Goals.reached(3, roops.util.Verdict.REACHABLE);}*/
			for (int i = firstIndex; i < lastIndex; i++) {
				/*{roops.util.Goals.reached(8, roops.util.Verdict.REACHABLE);}*/
				if (array[i] == null) {
					/*{roops.util.Goals.reached(10, roops.util.Verdict.REACHABLE);}*/
					return true;
				} else {
					/*{roops.util.Goals.reached(11, roops.util.Verdict.REACHABLE);}*/
				}
			}
			/*{roops.util.Goals.reached(9, roops.util.Verdict.REACHABLE);}*/
		}
		return false;
	}

	private boolean object_equals(Object object1, Object object2) {
		return object1 == object2;
	}

	public boolean add(Object object) {
		if (lastIndex == array.length) {
			/*{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}*/
			growAtEnd(1);
		} else {
			/*{roops.util.Goals.reached(3, roops.util.Verdict.REACHABLE);}*/
		}
		array[lastIndex] = object;
		lastIndex++;
		modCount++;
		return true;
	}

	private void growAtEnd(int required) {
		int size = lastIndex - firstIndex;
		int sufix_length = array.length - lastIndex;
		if (firstIndex >= required - sufix_length) {
			/*{roops.util.Goals.reached(4, roops.util.Verdict.REACHABLE);}*/
			
			int newLast = lastIndex - firstIndex;
			if (size > 0) {
				/*{roops.util.Goals.reached(6, roops.util.Verdict.REACHABLE);}*/
				add_system_arraycopy(array, firstIndex, array, 0, size);

				int start;
				if (newLast < firstIndex) {
					/*{roops.util.Goals.reached(8, roops.util.Verdict.REACHABLE);}*/
					start = firstIndex;
				} else {
					/*{roops.util.Goals.reached(9, roops.util.Verdict.REACHABLE);}*/
					start = newLast;
				}

				arrays_fill(start);
			} else {
				/*{roops.util.Goals.reached(7, roops.util.Verdict.REACHABLE);}*/
			}
			firstIndex = 0;
			lastIndex = newLast;
		} else {

			/*{roops.util.Goals.reached(5, roops.util.Verdict.REACHABLE);}*/

			int increment = size >> 1;

			if (required > increment) {
				/*{roops.util.Goals.reached(10, roops.util.Verdict.REACHABLE);}*/
				increment = required;
			} else {
				/*{roops.util.Goals.reached(11, roops.util.Verdict.REACHABLE);}*/
			}
			if (increment < 12) {
				/*{roops.util.Goals.reached(12, roops.util.Verdict.REACHABLE);}*/
				increment = 12;
			} else {
				/*{roops.util.Goals.reached(13, roops.util.Verdict.REACHABLE);}*/
			}
			Object[] newArray = newElementArray(size + increment);
			if (size > 0) {
				/*{roops.util.Goals.reached(14, roops.util.Verdict.REACHABLE);}*/				
				add_system_arraycopy(array, firstIndex, newArray, 0, size);
				firstIndex = 0;
				lastIndex = size;
			} else {
				/*{roops.util.Goals.reached(15, roops.util.Verdict.REACHABLE);}*/
			}
			array = newArray;
			
		}
		
	}

	private static Object[] newElementArray(int size) {
		Object[] result = new Object[size];
		return result;
	}

	public Object remove(int location) {
		Object result;
		int size = lastIndex - firstIndex;
		if (0 <= location && location < size) {
			/*{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}*/
			if (location == size - 1) {
				/*{roops.util.Goals.reached(4, roops.util.Verdict.REACHABLE);}*/
				lastIndex--;
				result = array[lastIndex];
				array[lastIndex] = null;
			} else {
				/*{roops.util.Goals.reached(5, roops.util.Verdict.REACHABLE);}*/

			  if (location == 0) {
				/*{roops.util.Goals.reached(6, roops.util.Verdict.REACHABLE);}*/
				result = array[firstIndex];
				array[firstIndex] = null;
				firstIndex++;
			  } else {
				/*{roops.util.Goals.reached(7, roops.util.Verdict.REACHABLE);}*/
     			int elementIndex = firstIndex + location;
				result = array[elementIndex];
				int size_div_2 = size >> 1;
				if (location < size_div_2) {
					/*{roops.util.Goals.reached(8, roops.util.Verdict.REACHABLE);}*/
					system_arraycopy(array, firstIndex, array, firstIndex + 1, location);
					array[firstIndex] = null;
					firstIndex++;
				} else {
					/*{roops.util.Goals.reached(9, roops.util.Verdict.REACHABLE);}*/
					system_arraycopy(array, elementIndex + 1, array, elementIndex, size - location - 1);
					lastIndex--;
					array[lastIndex] = null;
				}
			  }
		    }
			
			if (firstIndex == lastIndex) {
				firstIndex = lastIndex = 0;
			}
		} else {
			/*{roops.util.Goals.reached(3, roops.util.Verdict.REACHABLE);}*/
			throw new IndexOutOfBoundsException();
		}

		modCount++;
		return result;
	}

	private void system_arraycopy(Object[] src, int srcPos, Object[] dest, int destPos, int length) {
		for (int i = 0; i < length; i++) {
			/*{roops.util.Goals.reached(10, roops.util.Verdict.REACHABLE);}*/
			int srcIndex = srcPos + i;
			int destIndex = destPos + i;
			dest[destIndex] = src[srcIndex];
		}
		/*{roops.util.Goals.reached(11, roops.util.Verdict.REACHABLE);}*/
	}

	private void add_system_arraycopy(Object[] src, int srcPos, Object[] dest, int destPos, int length) {
		for (int i = 0; i < length; i++) {
			/*{roops.util.Goals.reached(18, roops.util.Verdict.REACHABLE);}*/
			int srcIndex = srcPos + i;
			int destIndex = destPos + i;
			dest[destIndex] = src[srcIndex];
		}
		/*{roops.util.Goals.reached(19, roops.util.Verdict.REACHABLE);}*/
	}

	private void arrays_fill(int start) {
		for (int i = start; i < array.length; i++) {
			/*{roops.util.Goals.reached(16, roops.util.Verdict.REACHABLE);}*/
			array[i] = null;
		}
		/*{roops.util.Goals.reached(17, roops.util.Verdict.REACHABLE);}*/
	}
	
	/**
	 * @Modifies_Everything;
	 *
	 * @Ensures return==true;
	 */
	static public boolean smallObjectArray(/*@ nullable @*/ ArrayList arrayList, /*@ nullable @*/ Object o) {
		if (arrayList != null) {
			if (arrayList.array.length==3) {
				if (arrayList.array[1]==arrayList.array) {
				  return false;
				}
			} 
		}
		return true;
	}


}
