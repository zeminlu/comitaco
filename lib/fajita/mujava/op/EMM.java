////////////////////////////////////////////////////////////////////////////
// Module : EMM.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op;

import java.io.*;
import openjava.mop.*;
import openjava.ptree.*;

/**
 * <p>Generate EMM (Java-specific modifier method change) mutants --
 *    change the modifier method name for other compatible modifier 
 *    method names. Note: <i>compatible</i> means that the signatures
 *    are the same except the method name
 * </p>
 * <p><i>Example</i>: point.setX(2); is mutated to point.setY(2);
 * </p> 
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class EMM extends mujava.op.util.Mutator
{
   public EMM(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
	  super( file_env,comp_unit );
   }

   public void visit( MethodCall p ) throws ParseTreeException 
   {
      int i;
      MethodCall mutant= null;
      String method_name = p.getName();
    
      if ((method_name.indexOf("set") == 0) && (p.getArguments().size() == 1))
      {
         Environment env = getEnvironment();
         Expression ref = p.getReferenceExpr();

         // defined in same class
         OJClass bindedtype=null;
         if ( ref == null ) 
         {
	        bindedtype = env.lookupClass(env.currentClassName());
         }
         else if (ref instanceof Variable)
         {
	        bindedtype = env.lookupBind(ref.toString());
         }

         if (bindedtype != null)
         {
            try
            {
               OJMethod[] m = bindedtype.getDeclaredMethods();
               boolean[] find_flag = new boolean[m.length];
               int method_index = -1;
               for ( i=0; i<m.length ; i++)
               {
                  find_flag[i]=false;
                  // find my method
                  if ( m[i].getName().equals(method_name))
                  {
                     method_index = i;
                     break;
                  }
               }

               if (method_index == -1)
               {
                  m = bindedtype.getAllMethods();
                  find_flag = new boolean[m.length];
                  for ( i=0; i<m.length ; i++)
                  {
                     find_flag[i] = false;
                     // find my method
                     if ( m[i].getName().equals(method_name))
                     {
                        method_index = i;
                        break;
                     }
                  }
               }
 
               if (method_index == -1) 
            	  return;

               int set_num = 0;
               for ( i=0; i<m.length; i++)
               {
                  if ( (i != method_index) && (m[i].getName().indexOf("set") == 0)
                      && sameReturnType(m[i], m[method_index])
                      && sameParameterType(m[i], m[method_index])) 
                  {
                     find_flag[i] = true;
                     set_num++;
                  }
               }
               
               if (set_num > 0)
               {
                  for (i=0; i<m.length; i++)
                  {
                     if (find_flag[i])
                     {
                        mutant = (MethodCall)p.makeRecursiveCopy();
                        mutant.setName(m[i].getName());
                        outputToFile(p, mutant);
                     }
                  }
                  return;
               }
            } catch (Exception e) {
               System.err.println(" [error] " + e);
               e.printStackTrace();
            }
         }
      }
 
      Expression newp = this.evaluateDown( p );
      if (newp != p) 
      {
         p.replace( newp );
         return;
      }

      p.childrenAccept( this );
      newp = this.evaluateUp( p );
      if (newp != p)  
    	 p.replace( newp );
   }

   /**
    * Output EMM mutants to files
    * @param original
    * @param mutant
    */
   public void outputToFile (MethodCall original, MethodCall mutant)
   {
      if (comp_unit == null) 
    	 return;

      String f_name;
      num++;
      f_name = getSourceName(this);
      String mutant_dir = getMuantID();

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 EMM_Writer writer = new EMM_Writer( mutant_dir, out );
		 writer.setMutant(original, mutant);
		 comp_unit.accept( writer );
		 out.flush();  
		 out.close();
      } catch ( IOException e ) {
      	 System.err.println( "fails to create " + f_name ); 
      } catch ( ParseTreeException e ) {
	     System.err.println( "errors during printing " + f_name );
	     e.printStackTrace();
      }
   }

}
