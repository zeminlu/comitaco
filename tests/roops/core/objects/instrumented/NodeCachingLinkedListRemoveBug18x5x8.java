package roops.core.objects;

import roops.core.objects.LinkedListNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;
/**
 * @j2daType
 */
/*@ nullable_by_default @*/public class NodeCachingLinkedListRemoveBug18x5x8 {

    public roops.core.objects.LinkedListNode header;

    public roops.core.objects.LinkedListNode firstCachedNode;

    public int maximumCacheSize;

    public int cacheSize;

    public int size;

    public int DEFAULT_MAXIMUM_CACHE_SIZE;

    public int modCount;

    public NodeCachingLinkedListRemoveBug18x5x8 () {
        this.header = new roops.core.objects.LinkedListNode ();
        this.header.next = this.header;
        this.header.previous = this.header;
        this.firstCachedNode = null;
        this.size = 0;
        this.cacheSize = 0;
        this.DEFAULT_MAXIMUM_CACHE_SIZE = 3;
        this.maximumCacheSize = 3;
        this.modCount = 0;
    }

    /*@
      @ invariant this.header!=null &&
      @           this.header.next!=null &&
      @           this.header.previous!=null &&
      @
      @           (\forall LinkedListNode n; \reach(this.header,LinkedListNode,next).has(n); n!=null && n.previous!=null && n.previous.next==n && n.next!=null && n.next.previous==n ) &&
      @
      @           this.size + 1 == \reach(this.header,LinkedListNode,next).int_size() &&
      @           this.size>=0;
      @
      @ invariant (\forall LinkedListNode m; \reach(this.firstCachedNode, LinkedListNode, next).has(m);
      @                                   \reach(m.next, LinkedListNode, next).has(m)==false &&
      @                                   m.previous==null
      @                                   );
      @
      @ invariant this.cacheSize <= this.maximumCacheSize;
      @
      @ invariant this.DEFAULT_MAXIMUM_CACHE_SIZE == 3;
      @
      @ invariant this.cacheSize == \reach(this.firstCachedNode, LinkedListNode, next).int_size();
      @*/

    /*@
    	  @  requires index>=0 && index<this.size;
    	  @  requires this.maximumCacheSize == this.DEFAULT_MAXIMUM_CACHE_SIZE;
    	  @  ensures this.size == \old(this.size) - 1;
    	  @  ensures \old(cacheSize) < maximumCacheSize ==> cacheSize == \old(cacheSize) + 1;
    	  @  ensures this.modCount == \old(this.modCount) + 1;
    	  @  ensures (index == 0 && size > 0) ==> \result == \old(this.header.next.value);
    	  @  ensures (index == 1 && size > 1) ==> \result == \old(this.header.next.next.value);
    	  @  ensures (index == 2 && size > 2) ==> \result == \old(this.header.next.next.next.value);
    	  @  ensures (\forall LinkedListNode n; \reach(header, LinkedListNode, next).has(n); \old(\reach(header, LinkedListNode, next)).has(n));
    	  @  ensures (\exists LinkedListNode n; \old(\reach(header, LinkedListNode, next)).has(n); \reach(header, LinkedListNode, next).has(n) == false);
    	  @  ensures (\forall LinkedListNode n; \old(\reach(firstCachedNode, LinkedListNode, next)).has(n); \reach(firstCachedNode, LinkedListNode, next).has(n));
    	  @  ensures (\forall LinkedListNode n; \old(\reach(firstCachedNode, LinkedListNode, next)).has(n); n.previous == null);
    	  @  ensures this.maximumCacheSize == this.DEFAULT_MAXIMUM_CACHE_SIZE;
    	  @  signals (Exception e) false;
    	  @*/
    public /*@nullable@*/java.lang.Object remove ( final int index) {
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=75\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark72(); //lineNumber=76\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark72();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"LinkedListNode node=null; //lineNumber=77\n");
		} catch (IOException ioexception) {
		}
		LinkedListNode node = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark73(); //lineNumber=78\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark73();
        if ( index < 0 ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(!(index < 0)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_0=true; //lineNumber=80\n");
			} catch (IOException ioexception) {
			}
			roops_goal_0 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark74(); //lineNumber=81\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark74();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"throw new java.lang.RuntimeException(); //lineNumber=82\n");
			} catch (IOException ioexception) {
			}
			throw new java.lang.RuntimeException();
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(index < 0){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_1=true; //lineNumber=84\n");
			} catch (IOException ioexception) {
			}
			roops_goal_1 = true;
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark76(); //lineNumber=86\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark76();
        if ( index > this.size ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(!(index > this.size)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_2=true; //lineNumber=88\n");
			} catch (IOException ioexception) {
			}
			roops_goal_2 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark77(); //lineNumber=89\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark77();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"throw new java.lang.RuntimeException(); //lineNumber=90\n");
			} catch (IOException ioexception) {
			}
			throw new java.lang.RuntimeException();
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(index > this.size){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_3=true; //lineNumber=92\n");
			} catch (IOException ioexception) {
			}
			roops_goal_3 = true;
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark79(); //lineNumber=94\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark79();
        if ( index > this.size ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(!(index > this.size)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_4=true; //lineNumber=96\n");
			} catch (IOException ioexception) {
			}
			roops_goal_4 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark80(); //lineNumber=97\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark80();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"throw new java.lang.IndexOutOfBoundsException(); //lineNumber=98\n");
			} catch (IOException ioexception) {
			}
			throw new java.lang.IndexOutOfBoundsException();
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(index > this.size){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_5=true; //lineNumber=100\n");
			} catch (IOException ioexception) {
			}
			roops_goal_5 = true;
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark82(); //lineNumber=102\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark82();
        if ( index < this.size / 2 ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(!(index < this.size / 2)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_6=true; //lineNumber=104\n");
			} catch (IOException ioexception) {
			}
			roops_goal_6 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark83(); //lineNumber=105\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark83();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"node=this.header.next; //lineNumber=106\n");
			} catch (IOException ioexception) {
			}
			node = this.header.next;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark84(); //lineNumber=107\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark84();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"int currentIndex=0; //lineNumber=108\n");
			} catch (IOException ioexception) {
			}
			int currentIndex = 0;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark85(); //lineNumber=109\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark85();
            {
                if ( currentIndex < index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex < index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_8=true; //lineNumber=112\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark86(); //lineNumber=113\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark86();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"node=node.next; //lineNumber=114\n");
					} catch (IOException ioexception) {
					}
					node = node.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark87(); //lineNumber=115\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark87();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex++; //lineNumber=116\n");
					} catch (IOException ioexception) {
					}
					currentIndex++;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark88(); //lineNumber=117\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark88();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex < index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_9=true; //lineNumber=119\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                if ( currentIndex < index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex < index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_10=true; //lineNumber=122\n");
					} catch (IOException ioexception) {
					}
					roops_goal_10 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark86(); //lineNumber=123\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark86();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"node=node.next; //lineNumber=124\n");
					} catch (IOException ioexception) {
					}
					node = node.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark87(); //lineNumber=125\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark87();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex++; //lineNumber=126\n");
					} catch (IOException ioexception) {
					}
					currentIndex++;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark88(); //lineNumber=127\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark88();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex < index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_11=true; //lineNumber=129\n");
					} catch (IOException ioexception) {
					}
					roops_goal_11 = true;
                }
                if ( currentIndex < index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex < index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_12=true; //lineNumber=132\n");
					} catch (IOException ioexception) {
					}
					roops_goal_12 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark86(); //lineNumber=133\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark86();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"node=node.next; //lineNumber=134\n");
					} catch (IOException ioexception) {
					}
					node = node.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark87(); //lineNumber=135\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark87();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex++; //lineNumber=136\n");
					} catch (IOException ioexception) {
					}
					currentIndex++;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark88(); //lineNumber=137\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark88();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex < index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_13=true; //lineNumber=139\n");
					} catch (IOException ioexception) {
					}
					roops_goal_13 = true;
                }
                if ( currentIndex < index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex < index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_14=true; //lineNumber=142\n");
					} catch (IOException ioexception) {
					}
					roops_goal_14 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark86(); //lineNumber=143\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark86();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"node=node.next; //lineNumber=144\n");
					} catch (IOException ioexception) {
					}
					node = node.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark87(); //lineNumber=145\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark87();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex++; //lineNumber=146\n");
					} catch (IOException ioexception) {
					}
					currentIndex++;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark88(); //lineNumber=147\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark88();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex < index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_15=true; //lineNumber=149\n");
					} catch (IOException ioexception) {
					}
					roops_goal_15 = true;
                }
                if ( currentIndex < index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex < index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_16=true; //lineNumber=152\n");
					} catch (IOException ioexception) {
					}
					roops_goal_16 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark86(); //lineNumber=153\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark86();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"node=node.next; //lineNumber=154\n");
					} catch (IOException ioexception) {
					}
					node = node.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark87(); //lineNumber=155\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark87();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex++; //lineNumber=156\n");
					} catch (IOException ioexception) {
					}
					currentIndex++;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark88(); //lineNumber=157\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark88();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex < index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_17=true; //lineNumber=159\n");
					} catch (IOException ioexception) {
					}
					roops_goal_17 = true;
                }
            }
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark89(); //lineNumber=162\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark89();
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(index < this.size / 2){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_7=true; //lineNumber=164\n");
			} catch (IOException ioexception) {
			}
			roops_goal_7 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark90(); //lineNumber=165\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark90();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"node=this.header; //lineNumber=166\n");
			} catch (IOException ioexception) {
			}
			node = this.header;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark91(); //lineNumber=167\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark91();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"int currentIndex=this.size; //lineNumber=168\n");
			} catch (IOException ioexception) {
			}
			int currentIndex = this.size;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark92(); //lineNumber=169\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark92();
            {
                if ( currentIndex > index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex > index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_18=true; //lineNumber=172\n");
					} catch (IOException ioexception) {
					}
					roops_goal_18 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark93(); //lineNumber=173\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"\nnode=node.previous; //mutGenLimit 1 //lineNumber=174\n");
					} catch (IOException ioexception) {
					}
					node = node.previous;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark94(); //lineNumber=175\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark94();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex=currentIndex - 1; //lineNumber=177\n");
					} catch (IOException ioexception) {
					}
					currentIndex = currentIndex - 1;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark95(); //lineNumber=178\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark95();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex > index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_19=true; //lineNumber=180\n");
					} catch (IOException ioexception) {
					}
					roops_goal_19 = true;
                }
                if ( currentIndex > index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex > index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_20=true; //lineNumber=183\n");
					} catch (IOException ioexception) {
					}
					roops_goal_20 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark93(); //lineNumber=184\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"\nnode=node.previous; //mutGenLimit 1 //lineNumber=185\n");
					} catch (IOException ioexception) {
					}
					node = node.previous;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark94(); //lineNumber=186\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark94();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex=currentIndex - 1; //lineNumber=188\n");
					} catch (IOException ioexception) {
					}
					currentIndex = currentIndex - 1;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark95(); //lineNumber=189\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark95();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex > index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_21=true; //lineNumber=191\n");
					} catch (IOException ioexception) {
					}
					roops_goal_21 = true;
                }
                if ( currentIndex > index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex > index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_22=true; //lineNumber=194\n");
					} catch (IOException ioexception) {
					}
					roops_goal_22 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark93(); //lineNumber=195\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"\nnode=node.previous; //mutGenLimit 1 //lineNumber=196\n");
					} catch (IOException ioexception) {
					}
					node = node.previous;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark94(); //lineNumber=197\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark94();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex=currentIndex - 1; //lineNumber=199\n");
					} catch (IOException ioexception) {
					}
					currentIndex = currentIndex - 1;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark95(); //lineNumber=200\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark95();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex > index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_23=true; //lineNumber=202\n");
					} catch (IOException ioexception) {
					}
					roops_goal_23 = true;
                }
                if ( currentIndex > index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex > index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_24=true; //lineNumber=205\n");
					} catch (IOException ioexception) {
					}
					roops_goal_24 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark93(); //lineNumber=206\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"\nnode=node.previous; //mutGenLimit 1 //lineNumber=207\n");
					} catch (IOException ioexception) {
					}
					node = node.previous;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark94(); //lineNumber=208\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark94();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex=currentIndex - 1; //lineNumber=210\n");
					} catch (IOException ioexception) {
					}
					currentIndex = currentIndex - 1;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark95(); //lineNumber=211\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark95();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex > index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_25=true; //lineNumber=213\n");
					} catch (IOException ioexception) {
					}
					roops_goal_25 = true;
                }
                if ( currentIndex > index ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(!(currentIndex > index)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_26=true; //lineNumber=216\n");
					} catch (IOException ioexception) {
					}
					roops_goal_26 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark93(); //lineNumber=217\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"\nnode=node.previous; //mutGenLimit 1 //lineNumber=218\n");
					} catch (IOException ioexception) {
					}
					node = node.previous;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark94(); //lineNumber=219\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark94();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"currentIndex=currentIndex - 1; //lineNumber=221\n");
					} catch (IOException ioexception) {
					}
					currentIndex = currentIndex - 1;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"__marker__.mark95(); //lineNumber=222\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark95();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"if(currentIndex > index){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
										"roops_goal_27=true; //lineNumber=224\n");
					} catch (IOException ioexception) {
					}
					roops_goal_27 = true;
                }
            }
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark96(); //lineNumber=227\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark96();
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark97(); //lineNumber=229\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark97();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"java.lang.Object oldValue; //lineNumber=230\n");
		} catch (IOException ioexception) {
		}
		java.lang.Object oldValue;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark98(); //lineNumber=231\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark98();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"oldValue=node.value; //lineNumber=232\n");
		} catch (IOException ioexception) {
		}
		oldValue = node.value;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark99(); //lineNumber=233\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark99();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"node.previous.next=node.next; //lineNumber=234\n");
		} catch (IOException ioexception) {
		}
		node.previous.next = node.next;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark100(); //lineNumber=235\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark100();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"node.next.previous=node.previous; //lineNumber=236\n");
		} catch (IOException ioexception) {
		}
		node.next.previous = node.previous;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark101(); //lineNumber=237\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark101();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"this.size=this.size - 1; //lineNumber=238\n");
		} catch (IOException ioexception) {
		}
		this.size = this.size - 1;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark102(); //lineNumber=239\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark102();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"this.modCount=this.modCount + 1; //lineNumber=240\n");
		} catch (IOException ioexception) {
		}
		this.modCount = this.modCount + 1;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark103(); //lineNumber=241\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark103();
        if ( this.cacheSize < this.maximumCacheSize ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(!(this.cacheSize < this.maximumCacheSize)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_28=true; //lineNumber=243\n");
			} catch (IOException ioexception) {
			}
			roops_goal_28 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark104(); //lineNumber=244\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark104();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops.core.objects.LinkedListNode nextCachedNode; //lineNumber=245\n");
			} catch (IOException ioexception) {
			}
			roops.core.objects.LinkedListNode nextCachedNode;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark105(); //lineNumber=246\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark105();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"nextCachedNode=this.firstCachedNode; //lineNumber=247\n");
			} catch (IOException ioexception) {
			}
			nextCachedNode = this.firstCachedNode;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark106(); //lineNumber=248\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark106();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"node.previous=null; //lineNumber=249\n");
			} catch (IOException ioexception) {
			}
			node.previous = null;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark107(); //lineNumber=250\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark107();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"node.next=nextCachedNode; //lineNumber=251\n");
			} catch (IOException ioexception) {
			}
			node.next = nextCachedNode;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark108(); //lineNumber=252\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark108();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"node.value=null; //lineNumber=253\n");
			} catch (IOException ioexception) {
			}
			node.value = null;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark109(); //lineNumber=254\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark109();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"this.firstCachedNode=node; //lineNumber=255\n");
			} catch (IOException ioexception) {
			}
			this.firstCachedNode = node;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark110(); //lineNumber=256\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark110();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"this.cacheSize=this.cacheSize - 1; //lineNumber=257\n");
			} catch (IOException ioexception) {
			}
			this.cacheSize = this.cacheSize - 1;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"__marker__.mark111(); //lineNumber=258\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark111();
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"if(this.cacheSize < this.maximumCacheSize){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
								"roops_goal_29=true; //lineNumber=260\n");
			} catch (IOException ioexception) {
			}
			roops_goal_29 = true;
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"__marker__.mark112(); //lineNumber=262\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark112();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_remove",
							"return oldValue; //lineNumber=263\n");
		} catch (IOException ioexception) {
		}
		return oldValue;
    }

    /*@ requires true;
    @ ensures size == \old(size) + 1;
    @ ensures modCount == \old(modCount) + 1;
    @ ensures ( \forall LinkedListNode n; \old(\reach(header, LinkedListNode, next)).has(n); \reach(header, LinkedListNode, next).has(n));
    @ ensures ( \forall LinkedListNode n; \reach(header, LinkedListNode, next).has(n) && n != header.next; \old(\reach(header, LinkedListNode, next)).has(n) );
    @ ensures ( header.next.value == o );
    @ ensures \result == true;
    @*/
    public boolean addFirst ( java.lang.Object o) {
        LinkedListNode newNode = new LinkedListNode (); //mutGenLimit 0
        newNode
        .value = o; //mutGenLimit 0
        LinkedListNode insertBeforeNode = this.header.next; //mutGenLimit 0
        newNode
        .next = insertBeforeNode; //mutGenLimit 0
        newNode
        .previous = insertBeforeNode.previous; //mutGenLimit 0
        insertBeforeNode
        .previous.next = newNode; //mutGenLimit 0
        insertBeforeNode
        .previous = newNode; //mutGenLimit 0
        this
        .size ++; //mutGenLimit 0
        this
        .modCount ++; //mutGenLimit 0
        return true; //mutGenLimit 0
    }

    /*@
    @ requires true;
    @ ensures \result == true <==> (\exists LinkedListNode n; \reach(header, LinkedListNode, next).has(n) && n != header; n.value == arg);
    @*/
    public boolean contains ( /*@ nullable @*/java.lang.Object arg) {
        LinkedListNode node = this.header.next; //mutGenLimit 0
        {
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
        }
        return false; //mutGenLimit 0
    }

    public static boolean roops_goal_0;

    public static boolean roops_goal_1;

    public static boolean roops_goal_2;

    public static boolean roops_goal_3;

    public static boolean roops_goal_4;

    public static boolean roops_goal_5;

    public static boolean roops_goal_6;

    public static boolean roops_goal_7;

    public static boolean roops_goal_8;

    public static boolean roops_goal_9;

    public static boolean roops_goal_10;

    public static boolean roops_goal_11;

    public static boolean roops_goal_12;

    public static boolean roops_goal_13;

    public static boolean roops_goal_14;

    public static boolean roops_goal_15;

    public static boolean roops_goal_16;

    public static boolean roops_goal_17;

    public static boolean roops_goal_18;

    public static boolean roops_goal_19;

    public static boolean roops_goal_20;

    public static boolean roops_goal_21;

    public static boolean roops_goal_22;

    public static boolean roops_goal_23;

    public static boolean roops_goal_24;

    public static boolean roops_goal_25;

    public static boolean roops_goal_26;

    public static boolean roops_goal_27;

    public static boolean roops_goal_28;

    public static boolean roops_goal_29;

    public static void fajita_roopsGoal_initialization () {
        roops_goal_0 = false;
        roops_goal_1 = false;
        roops_goal_2 = false;
        roops_goal_3 = false;
        roops_goal_4 = false;
        roops_goal_5 = false;
        roops_goal_6 = false;
        roops_goal_7 = false;
        roops_goal_8 = false;
        roops_goal_9 = false;
        roops_goal_10 = false;
        roops_goal_11 = false;
        roops_goal_12 = false;
        roops_goal_13 = false;
        roops_goal_14 = false;
        roops_goal_15 = false;
        roops_goal_16 = false;
        roops_goal_17 = false;
        roops_goal_18 = false;
        roops_goal_19 = false;
        roops_goal_20 = false;
        roops_goal_21 = false;
        roops_goal_22 = false;
        roops_goal_23 = false;
        roops_goal_24 = false;
        roops_goal_25 = false;
        roops_goal_26 = false;
        roops_goal_27 = false;
        roops_goal_28 = false;
        roops_goal_29 = false;
    }
}
