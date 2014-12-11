package tests.roops.core.objects.instrumented.ar.edu.itba.linedetector;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;


/*@ nullable_by_default @*/
public class SinglyLinkedList 
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



    /*@
        @ invariant (\forall SinglyLinkedListNode n; reach(header, SinglyLinkedListNode.class," next").has(n);  reach(n.next, SinglyLinkedListNode.class," next").has(n)==false); 
        @*/
    public roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedList () {
    }

//----------------- showInstance --------------------//
    /*@ requires reach(this.header, SinglyLinkedListNode.class," next").int_size() == 100; 
        @ ensures \result == false;
        @*/
    public boolean showInstance () {
        return true;
    }

//-------------------- contains -------------------------//
    /*@
        @ ensures (\exists SinglyLinkedListNode n; reach(this.header, SinglyLinkedListNode.class," next").has(n); n.value==valueParam) 
        @     <==> (\result==true);
        @ signals (RuntimeException e) false;
        @*/
    public boolean contains (  /*@nullable@*/ java.lang.Object valueParam) {
        roops.core.objects.SinglyLinkedListNode current;
        boolean result;
        current = this.header;
        result = false;
        {
            boolean terminatesInTime = false;
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                terminatesInTime = true;
            } else {
            }
        }
        return result;
    }

//--------------------------- getNode ----------------------------//
    /*@
        @ requires index>=0 && index<reach(this.header, SinglyLinkedListNode.class," next").int_size(); 
        @ ensures reach(this.header, SinglyLinkedListNode.class," next").has(\result)==true; 
        @ ensures reach(\result, SinglyLinkedListNode.class," next").int_size() ==  reach(this.header, SinglyLinkedListNode.class," next").int_size()-index; 
        @ signals (Exception e) false;
        @*/
    public roops.core.objects.SinglyLinkedListNode getNode ( int index) {
        roops.core.objects.SinglyLinkedListNode current = header;
        roops.core.objects.SinglyLinkedListNode result = null;
        int current_index = 0;
        {
            boolean terminatesInTime = false;
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                terminatesInTime = true;
            } else {
            }
        }
        return result;
    }

//------------------------ insertBack --------------------------//
//Due to jml4c the ensures clauses must be in that order :(

    /*@
        @ requires freshNode!=null;
        @ requires reach(header, SinglyLinkedListNode.class," next").has(freshNode)==false; 
        @ ensures reach(header, SinglyLinkedListNode.class," next").int_size()==\old( reach(header, SinglyLinkedListNode.class," next")).int_size()+1; 
        @ ensures (\forall SinglyLinkedListNode n;
        @            \old(reach(header, SinglyLinkedListNode.class," next")).has(n); 
        @      reach(header, SinglyLinkedListNode.class," next").has(n)==true 
        @         );
        @ ensures (\exists SinglyLinkedListNode n;
        @            reach(header, SinglyLinkedListNode.class," next").has(n); 
        @            n.next==null && n.value==data);
        @ signals (Exception e) false;
        @*/
    void insertBack ( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode) {
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=337\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
							"__marker__.mark(101); //lineNumber=338\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(101);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
							"freshNode.value=null; //lineNumber=339\n");
		} catch (IOException ioexception) {
		}
		freshNode.value = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
							"__marker__.mark(102); //lineNumber=340\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(102);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
							"freshNode.next=null; //lineNumber=341\n");
		} catch (IOException ioexception) {
		}
		freshNode.next = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
							"__marker__.mark(103); //lineNumber=342\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(103);
        if ( this.header == null ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"if(!(this.header == null)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"roops_goal_0=true; //lineNumber=344\n");
			} catch (IOException ioexception) {
			}
			roops_goal_0 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"__marker__.mark(104); //lineNumber=345\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(104);
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"this.header=freshNode; //lineNumber=346\n");
			} catch (IOException ioexception) {
			}
			this.header = freshNode;
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"__marker__.mark(105); //lineNumber=347\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(105);
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"if(this.header == null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"roops_goal_1=true; //lineNumber=349\n");
			} catch (IOException ioexception) {
			}
			roops_goal_1 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"__marker__.mark(106); //lineNumber=350\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(106);
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"roops.core.objects.SinglyLinkedListNode current=this.header; //lineNumber=351\n");
			} catch (IOException ioexception) {
			}
			roops.core.objects.SinglyLinkedListNode current = this.header;
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"__marker__.mark(107); //lineNumber=352\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(107);
            {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
									"boolean terminatesInTime=false; //lineNumber=354\n");
				} catch (IOException ioexception) {
				}
				boolean terminatesInTime = false;
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_2=true; //lineNumber=356\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(108); //lineNumber=357\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(108);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"current=current.next; //lineNumber=358\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(109); //lineNumber=359\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(109);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_3=true; //lineNumber=361\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_4=true; //lineNumber=364\n");
					} catch (IOException ioexception) {
					}
					roops_goal_4 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(108); //lineNumber=365\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(108);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"current=current.next; //lineNumber=366\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(109); //lineNumber=367\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(109);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_5=true; //lineNumber=369\n");
					} catch (IOException ioexception) {
					}
					roops_goal_5 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_6=true; //lineNumber=372\n");
					} catch (IOException ioexception) {
					}
					roops_goal_6 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(108); //lineNumber=373\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(108);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"current=current.next; //lineNumber=374\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(109); //lineNumber=375\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(109);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_7=true; //lineNumber=377\n");
					} catch (IOException ioexception) {
					}
					roops_goal_7 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_8=true; //lineNumber=380\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(108); //lineNumber=381\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(108);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"current=current.next; //lineNumber=382\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(109); //lineNumber=383\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(109);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_9=true; //lineNumber=385\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_10=true; //lineNumber=388\n");
					} catch (IOException ioexception) {
					}
					roops_goal_10 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(108); //lineNumber=389\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(108);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"current=current.next; //lineNumber=390\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(109); //lineNumber=391\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(109);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_11=true; //lineNumber=393\n");
					} catch (IOException ioexception) {
					}
					roops_goal_11 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_12=true; //lineNumber=396\n");
					} catch (IOException ioexception) {
					}
					roops_goal_12 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(108); //lineNumber=397\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(108);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"current=current.next; //lineNumber=398\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(109); //lineNumber=399\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(109);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_13=true; //lineNumber=401\n");
					} catch (IOException ioexception) {
					}
					roops_goal_13 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_14=true; //lineNumber=404\n");
					} catch (IOException ioexception) {
					}
					roops_goal_14 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(108); //lineNumber=405\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(108);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"current=current.next; //lineNumber=406\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(109); //lineNumber=407\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(109);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_15=true; //lineNumber=409\n");
					} catch (IOException ioexception) {
					}
					roops_goal_15 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_16=true; //lineNumber=412\n");
					} catch (IOException ioexception) {
					}
					roops_goal_16 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(108); //lineNumber=413\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(108);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"current=current.next; //lineNumber=414\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"__marker__.mark(109); //lineNumber=415\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(109);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_17=true; //lineNumber=417\n");
					} catch (IOException ioexception) {
					}
					roops_goal_17 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_18=true; //lineNumber=420\n");
					} catch (IOException ioexception) {
					}
					roops_goal_18 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"terminatesInTime=true; //lineNumber=421\n");
					} catch (IOException ioexception) {
					}
					terminatesInTime = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
										"roops_goal_19=true; //lineNumber=423\n");
					} catch (IOException ioexception) {
					}
					roops_goal_19 = true;
                }
            }
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"__marker__.mark(110); //lineNumber=426\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(110);
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"current.next=freshNode; //lineNumber=427\n");
			} catch (IOException ioexception) {
			}
			current.next = freshNode;
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
								"__marker__.mark(111); //lineNumber=428\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(111);
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_insertBack",
							"__marker__.mark(112);\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(112);
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
    }
}
