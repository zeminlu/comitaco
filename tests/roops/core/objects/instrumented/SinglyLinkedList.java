// This is mutant program.
// Author : ysma

package roops.core.objects.instrumented;


import roops.core.objects.SinglyLinkedListNode;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;


/*@ nullable_by_default @*/
public class SinglyLinkedList
{

/*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public SinglyLinkedListNode header;

    public SinglyLinkedList()
    {
    }

//----------------- showInstance --------------------//
/*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;    
    @ ensures \result == false;
    @*/
    public boolean showInstance()
    {
        return true;
    }

/*@
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param) <==> (\result==true);
    @ signals (Exception e) true;
    @*/
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param )
    {
        try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
							"SinglyLinkedListNode current;\n");
		} catch (IOException ioexception) {
		}
		SinglyLinkedListNode current;
        try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
							"boolean result;\n");
		} catch (IOException ioexception) {
		}
		boolean result;
<<<<<<< HEAD
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header;\n");
		} catch (IOException ioexception) {
		}
		current = this.header;
try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"//mutID 0\ncurrent=this.header.next; //mutGenLimit 1\n");
		} catch (IOException ioexception) {
		}
		current = this.header.next;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
							"result=false;\n");
		} catch (IOException ioexception) {
		}
		result = false;
        while (result == false && current != null) {
            try {
				FileUtils
						.appendToFile(
<<<<<<< HEAD
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
								"if(!(result == false && current != null)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
<<<<<<< HEAD
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
								"boolean equalVal;\n");
			} catch (IOException ioexception) {
			}
			boolean equalVal;
            if (value_param == null && current.value == null) {
<<<<<<< HEAD
<<<<<<< HEAD
            	try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"//mutID 0\nequalVal=true; //                equalVal = false; //mutGenLimit 1\n");
				} catch (IOException ioexception) {
				}
				equalVal = true;
=======
try {
=======
            	try {
>>>>>>> negate post
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"//mutID 1\nequalVal=true; //                equalVal = false; //mutGenLimit 1\n");
				} catch (IOException ioexception) {
				}
<<<<<<< HEAD
				equalVal = false;
>>>>>>> generating weird sequential code
=======
				equalVal = true;
>>>>>>> negate post
            } else {
                try {
					FileUtils
							.appendToFile(
<<<<<<< HEAD
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
									"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				if (value_param != null) {
                    try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
										"if(!(value_param != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					if (value_param == current.value) {
                        try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
											"if(!(value_param == current.value)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
											"equalVal=true;\n");
						} catch (IOException ioexception) {
						}
						equalVal = true;
                    } else {
                        try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
											"if(value_param == current.value){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
											"equalVal=false;\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                } else {
                    try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
										"if(value_param != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
										"equalVal=false;\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                }
            }
            if (equalVal == true) {
                try {
					FileUtils
							.appendToFile(
<<<<<<< HEAD
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
									"if(!(equalVal == true)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
<<<<<<< HEAD
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
									"result=true;\n");
				} catch (IOException ioexception) {
				}
				result = true;
            }
<<<<<<< HEAD
try {
<<<<<<< HEAD
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"//mutID 1\ncurrent=current.next.next; //mutGenLimit 1\n");
						} catch (IOException ioexception) {
						}
						current = current.next.next;
=======
=======
            try {
>>>>>>> negate post
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"//mutID 2\ncurrent=current.next; //            current = current.next.next; //mutGenLimit 1\n");
			} catch (IOException ioexception) {
			}
<<<<<<< HEAD
			current = current.next.next;
>>>>>>> generating weird sequential code
=======
			current = current.next;
>>>>>>> negate post
        }
		try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
							"if(result == false && current != null){throw new RuntimeException();}\n");
		} catch (IOException ioexception) {
		}
        try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"//mutID 2\nreturn result; //                return !result; //mutGenLimit 1\n");
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"//mutID 3\nreturn result; //                return !result; //mutGenLimit 1\n");
>>>>>>> generating weird sequential code
		} catch (IOException ioexception) {
		}
		return result;
    }

//--------------------------- getNode ----------------------------//    
/*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true; 
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (Exception e) false;
    @*/
    public SinglyLinkedListNode getNode( int index )
    {
        roops.core.objects.SinglyLinkedListNode current = header;
        roops.core.objects.SinglyLinkedListNode result = null;
        int current_index = 0;
        while (result == null && current != null) {
            if (index == current_index) {
                result = current;
            }
            current_index = current_index + 1;
            current = current.next;
        }
        return result;
    }

//------------------------ insertBack --------------------------//    
//Due to jml4c the ensures clauses must be in that order :(      
/*@
    @ requires freshNode!=null;
    @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false; 
    @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
    @ ensures (\forall SinglyLinkedListNode n; 
    @            \old(\reach(header, SinglyLinkedListNode, next)).has(n);
    @			 \reach(header, SinglyLinkedListNode, next).has(n)==true  
    @         );
    @ ensures (\exists SinglyLinkedListNode n; 
    @            \reach(header, SinglyLinkedListNode, next).has(n); 
    @            n.next==null && n.value==data);
    @ signals (Exception e) false;
    @*/
    void insertBack( java.lang.Object data, SinglyLinkedListNode freshNode )
    {
        freshNode.value = data;
        freshNode.next = null;
        if (this.header == null) {
            this.header = freshNode;
        } else {
            roops.core.objects.SinglyLinkedListNode current = this.header;
            while (current.next != null) {
                current = current.next;
            }
            current.next = freshNode;
        }
    }

}
