package tests.roops.core.objects.instrumented.ar.edu.itba.linedetector;

import roops.core.objects.LinkedListNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;
/**
 * @j2daType
 */
/*@ nullable_by_default @*/public class NodeCachingLinkedListRemoveBug18x5x8 
{
    protected static ar.edu.taco.stryker.api.impl.ReachSet reach(Object o, Class<?> clazz, String str) {
        String fieldsToMoveThroughAsStrings[] = str.replaceAll(" ", "").split("\\+");
        ar.edu.taco.stryker.api.impl.ReachSet objectSet = new ar.edu.taco.stryker.api.impl.ReachSet();
        java.util.IdentityHashMap<Object,Object> visitedObjects = new java.util.IdentityHashMap<Object,Object>();
        java.util.Queue<Object> queue = new java.util.LinkedList<Object>();
        
		 if(o == null) {
        	return objectSet;
		 }
        visitedObjects.put(o, o);
        queue.add(o);
		 objectSet.add(o);
        while(!queue.isEmpty()) {
            Object currentObject = queue.poll();
            
            java.util.List<java.lang.reflect.Field> fieldsToMoveThrough = new java.util.ArrayList<java.lang.reflect.Field>();
            for(int i = 0; i < fieldsToMoveThroughAsStrings.length; i++) {
                    String fieldAsString = fieldsToMoveThroughAsStrings[i];
                try {
                    java.lang.reflect.Field field = currentObject.getClass().getDeclaredField(fieldAsString);
                    fieldsToMoveThrough.add(field);
                } catch (NoSuchFieldException e) {
                e.printStackTrace();
                } catch (SecurityException e) {
                     e.printStackTrace();
                }
            }
            
            for(java.lang.reflect.Field field : fieldsToMoveThrough) {
                field.setAccessible(true);
                try {
                    Object accessedObject = field.get(currentObject);
                    if(accessedObject != null && visitedObjects.containsKey(accessedObject) == false) {
                        visitedObjects.put(accessedObject, accessedObject);
                        queue.add(accessedObject);
                        if(clazz.isInstance(accessedObject)) {
                            objectSet.add(accessedObject);
                        }
                    }
                } catch (IllegalArgumentException e) {
                e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return objectSet;
    }



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
      @           (\forall LinkedListNode n; reach(this.header,LinkedListNode.class,"next").has(n); n!=null && n.previous!=null && n.previous.next==n && n.next!=null && n.next.previous==n ) && 
      @
      @           this.size + 1 == reach(this.header,LinkedListNode.class,"next").int_size() && 
      @           this.size>=0;
      @
      @ invariant (\forall LinkedListNode m; reach(this.firstCachedNode, LinkedListNode.class," next").has(m); 
      @                                   reach(m.next, LinkedListNode.class," next").has(m)==false && 
      @                                   m.previous==null
      @                                   );
      @
      @ invariant this.cacheSize <= this.maximumCacheSize;
      @
      @ invariant this.DEFAULT_MAXIMUM_CACHE_SIZE == 3;
      @
      @ invariant this.cacheSize == reach(this.firstCachedNode, LinkedListNode.class," next").int_size(); 
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
    	  @  ensures (\forall LinkedListNode n; reach(header, LinkedListNode.class," next").has(n); \old( reach(header, LinkedListNode.class," next")).has(n)); 
    	  @  ensures (\exists LinkedListNode n; \old(reach(header, LinkedListNode.class," next")).has(n);  reach(header, LinkedListNode.class," next").has(n) == false); 
    	  @  ensures (\forall LinkedListNode n; \old(reach(firstCachedNode, LinkedListNode.class," next")).has(n);  reach(firstCachedNode, LinkedListNode.class," next").has(n)); 
    	  @  ensures (\forall LinkedListNode n; \old(reach(firstCachedNode, LinkedListNode.class," next")).has(n); n.previous == null); 
    	  @  ensures this.maximumCacheSize == this.DEFAULT_MAXIMUM_CACHE_SIZE;
    	  @  signals (Exception e) false;
    	  @*/
    public /*@nullable@*/java.lang.Object remove ( final int index) {
        LinkedListNode node = null;
        if ( index < 0 ) {
            throw new java.lang.RuntimeException ();
        } else {
        }
        if ( index < this.size ) { //mutGenLimit 1
            throw new java.lang.RuntimeException ();
        } else {
        }
        if ( index < this.size ) { //mutGenLimit 1
            throw new java.lang.IndexOutOfBoundsException ();
        } else {
        }
        boolean terminatesInTime = false;
        if ( index < this.size / 2 ) {
            node = this.header.next;
            int currentIndex = 0;
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                terminatesInTime = true;
            } else {
            }
        } else {
            node = this.header;
            int currentIndex = this.size;
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                terminatesInTime = true;
            } else {
            }
        }
        java.lang.Object oldValue;
        oldValue = node.value;
        node.previous.next = node.next;
        node.next.previous = node.previous;
        this.size = this.size - 1;
        this.modCount = this.modCount + 1;
        if ( this.cacheSize < this.maximumCacheSize ) {
            roops.core.objects.LinkedListNode nextCachedNode;
            nextCachedNode = this.firstCachedNode;
            node.previous = null;
            node.next = nextCachedNode;
            node.value = null;
            this.firstCachedNode = node;
            this.cacheSize = this.cacheSize + 1;
        } else {
        }
        return oldValue;
    }

    /*@ requires true;
    @ ensures size == \old(size) + 1;
    @ ensures modCount == \old(modCount) + 1;
    @ ensures ( \forall LinkedListNode n; \old(reach(header, LinkedListNode.class," next")).has(n);  reach(header, LinkedListNode.class," next").has(n)); 
    @ ensures ( \forall LinkedListNode n; reach(header, LinkedListNode.class," next").has(n) && n != header.next; \old( reach(header, LinkedListNode.class," next")).has(n) ); 
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
    @ ensures \result == true <==> (\exists LinkedListNode n; reach(header, LinkedListNode.class," next").has(n) && n != header; n.value == arg); 
    @*/
    public boolean contains ( /*@ nullable @*/java.lang.Object arg) {
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=215\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
							"__marker__.mark(142); //lineNumber=216\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(142);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
							"\nLinkedListNode node=this.header; //mutGenLimit 0 //lineNumber=217\n");
		} catch (IOException ioexception) {
		}
		LinkedListNode node = this.header;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
							"__marker__.mark(143); //lineNumber=218\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(143);
        {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
								"boolean terminatesInTime=false; //lineNumber=221\n");
			} catch (IOException ioexception) {
			}
			boolean terminatesInTime = false;
            if ( node != this.header ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nroops_goal_0=true; //mutGenLimit 0 //lineNumber=223\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(144); //lineNumber=224\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(144);
                if ( node.value == arg ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nroops_goal_2=true; //mutGenLimit 0 //lineNumber=226\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"__marker__.mark(145); //lineNumber=227\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(145);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nreturn true; //mutGenLimit 0 //lineNumber=228\n");
					} catch (IOException ioexception) {
					}
					return true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"if(node.value == arg){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"roops_goal_3=true; //lineNumber=230\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(147); //lineNumber=232\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(147);
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nnode=node.next; //mutGenLimit 0 //lineNumber=233\n");
				} catch (IOException ioexception) {
				}
				node = node.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(148); //lineNumber=234\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(148);
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"if(node != this.header){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"roops_goal_1=true; //lineNumber=237\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
            if ( node != this.header ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nroops_goal_4=true; //mutGenLimit 0 //lineNumber=240\n");
				} catch (IOException ioexception) {
				}
				roops_goal_4 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(144); //lineNumber=241\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(144);
                if ( node.value == arg ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nroops_goal_6=true; //mutGenLimit 0 //lineNumber=243\n");
					} catch (IOException ioexception) {
					}
					roops_goal_6 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"__marker__.mark(145); //lineNumber=244\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(145);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nreturn true; //mutGenLimit 0 //lineNumber=245\n");
					} catch (IOException ioexception) {
					}
					return true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"if(node.value == arg){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"roops_goal_7=true; //lineNumber=247\n");
					} catch (IOException ioexception) {
					}
					roops_goal_7 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(147); //lineNumber=249\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(147);
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nnode=node.next; //mutGenLimit 0 //lineNumber=250\n");
				} catch (IOException ioexception) {
				}
				node = node.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(148); //lineNumber=251\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(148);
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"if(node != this.header){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"roops_goal_5=true; //lineNumber=254\n");
				} catch (IOException ioexception) {
				}
				roops_goal_5 = true;
            }
            if ( node != this.header ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nroops_goal_8=true; //mutGenLimit 0 //lineNumber=257\n");
				} catch (IOException ioexception) {
				}
				roops_goal_8 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(144); //lineNumber=258\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(144);
                if ( node.value == arg ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nroops_goal_10=true; //mutGenLimit 0 //lineNumber=260\n");
					} catch (IOException ioexception) {
					}
					roops_goal_10 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"__marker__.mark(145); //lineNumber=261\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(145);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nreturn true; //mutGenLimit 0 //lineNumber=262\n");
					} catch (IOException ioexception) {
					}
					return true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"if(node.value == arg){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"roops_goal_11=true; //lineNumber=264\n");
					} catch (IOException ioexception) {
					}
					roops_goal_11 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(147); //lineNumber=266\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(147);
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nnode=node.next; //mutGenLimit 0 //lineNumber=267\n");
				} catch (IOException ioexception) {
				}
				node = node.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(148); //lineNumber=268\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(148);
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"if(node != this.header){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"roops_goal_9=true; //lineNumber=271\n");
				} catch (IOException ioexception) {
				}
				roops_goal_9 = true;
            }
            if ( node != this.header ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nroops_goal_12=true; //mutGenLimit 0 //lineNumber=274\n");
				} catch (IOException ioexception) {
				}
				roops_goal_12 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(144); //lineNumber=275\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(144);
                if ( node.value == arg ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nroops_goal_14=true; //mutGenLimit 0 //lineNumber=277\n");
					} catch (IOException ioexception) {
					}
					roops_goal_14 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"__marker__.mark(145); //lineNumber=278\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(145);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nreturn true; //mutGenLimit 0 //lineNumber=279\n");
					} catch (IOException ioexception) {
					}
					return true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"if(node.value == arg){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"roops_goal_15=true; //lineNumber=281\n");
					} catch (IOException ioexception) {
					}
					roops_goal_15 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(147); //lineNumber=283\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(147);
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nnode=node.next; //mutGenLimit 0 //lineNumber=284\n");
				} catch (IOException ioexception) {
				}
				node = node.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(148); //lineNumber=285\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(148);
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"if(node != this.header){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"roops_goal_13=true; //lineNumber=288\n");
				} catch (IOException ioexception) {
				}
				roops_goal_13 = true;
            }
            if ( node != this.header ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nroops_goal_16=true; //mutGenLimit 0 //lineNumber=291\n");
				} catch (IOException ioexception) {
				}
				roops_goal_16 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(144); //lineNumber=292\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(144);
                if ( node.value == arg ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nroops_goal_18=true; //mutGenLimit 0 //lineNumber=294\n");
					} catch (IOException ioexception) {
					}
					roops_goal_18 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"__marker__.mark(145); //lineNumber=295\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(145);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"\nreturn true; //mutGenLimit 0 //lineNumber=296\n");
					} catch (IOException ioexception) {
					}
					return true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"if(node.value == arg){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
										"roops_goal_19=true; //lineNumber=298\n");
					} catch (IOException ioexception) {
					}
					roops_goal_19 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(147); //lineNumber=300\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(147);
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"\nnode=node.next; //mutGenLimit 0 //lineNumber=301\n");
				} catch (IOException ioexception) {
				}
				node = node.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"__marker__.mark(148); //lineNumber=302\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(148);
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"if(node != this.header){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"roops_goal_17=true; //lineNumber=305\n");
				} catch (IOException ioexception) {
				}
				roops_goal_17 = true;
            }
            if ( node != this.header ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"if(!(node != this.header)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"roops_goal_20=true; //lineNumber=308\n");
				} catch (IOException ioexception) {
				}
				roops_goal_20 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"terminatesInTime=true; //lineNumber=309\n");
				} catch (IOException ioexception) {
				}
				terminatesInTime = true;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"if(node != this.header){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
									"roops_goal_21=true; //lineNumber=311\n");
				} catch (IOException ioexception) {
				}
				roops_goal_21 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
							"__marker__.mark(149); //lineNumber=314\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(149);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedListRemoveBug18x5x8.java_contains",
							"\nreturn false; //mutGenLimit 0 //lineNumber=315\n");
		} catch (IOException ioexception) {
		}
		return false;
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
    }
}
