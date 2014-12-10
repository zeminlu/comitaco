////////////////////////////////////////////////////////////////////////////
// Module : JTD.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op;

import java.io.*;
import java.util.Vector;
import openjava.mop.*;
import openjava.ptree.*;

/**
 * <p>Generate JTD (Java-specific this keyword deletion) --
 *    delete each occurrence of the keyword <i>this</i> 
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class JTD extends mujava.op.util.Mutator
{
   Vector instanceVar = new Vector();
   Vector localVar = new Vector();
   boolean isJTDTarget = false;

   public JTD(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
	  super( file_env, comp_unit );
   }

   public void visit( AssignmentExpression p ) throws ParseTreeException 
   {
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

   boolean isTarget( Parameter p ) throws ParseTreeException 
   {
      for (int i=0; i<instanceVar.size(); i++)
      {
         String field_name = instanceVar.get(i).toString();
         if (field_name.equals(p.getVariable()))
         {
            localVar.add(p.getVariable());
            return true;
         }
      }
      return false;
   }

   public void visit( FieldDeclaration p ) throws ParseTreeException 
   {
      instanceVar.add(p.getName());
   }

   public void visit( ConstructorDeclaration p ) throws ParseTreeException 
   {
      ParameterList plist = p.getParameters();
      localVar.removeAllElements();
      for (int i=0; i<plist.size(); i++)
      {
         if (isTarget(plist.get(i)))
         {
            isJTDTarget = true;
         }
      }
      if (isJTDTarget)
      {
         super.visit(p);
      }
      isJTDTarget = false;
   }

   public void visit( MethodDeclaration p ) throws ParseTreeException 
   {
      ParameterList plist = p.getParameters();
      localVar.removeAllElements();
      for (int i=0; i<plist.size(); i++)
      {
         if (isTarget(plist.get(i)))
         {
            isJTDTarget = true;
         }
      }
      if (isJTDTarget)
      {
         super.visit(p);
      }
      isJTDTarget = false;
   }

   public void visit( FieldAccess p ) throws ParseTreeException 
   {
	  Expression ref_exp = p.getReferenceExpr();
      String var_name = p.getName();
	  if (ref_exp instanceof SelfAccess)
	  {
		 if (((SelfAccess)ref_exp).getAccessType() == SelfAccess.THIS)
		 {
            for (int i=0; i<localVar.size(); i++)
            {
               if (var_name.equals(localVar.get(i).toString()))
               {
                  FieldAccess mutant = (FieldAccess)p.makeRecursiveCopy();
                  mutant.setReferenceExpr(null);
                  outputToFile(p, mutant);
               }
            }
		 }
      }
   }

   /**
    * Output JTD mutants to files
    * @param original
    * @param mutant
    */
   public void outputToFile(FieldAccess original, FieldAccess mutant)
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
		 ISK_JTD_Writer writer = new ISK_JTD_Writer(mutant_dir, out);
		 writer.setMutant(original, mutant);
		 comp_unit.accept( writer );
		 out.flush();  
		 out.close();
      } catch ( IOException e ) 
      {
		 System.err.println( "fails to create " + f_name );
      } catch ( ParseTreeException e ) 
      {
		 System.err.println( "errors during printing " + f_name );
		 e.printStackTrace();
      }
   }

   /**
    * Output JTD mutants to files
    * @param original
    * @param mutant
    */
   public void outputToFile(MethodCall original, MethodCall mutant)
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
	 	 ISK_JTD_Writer writer = new ISK_JTD_Writer(mutant_dir, out);
		 writer.setMutant(original, mutant);
		 comp_unit.accept( writer );
		 out.flush();  
		 out.close();
      } catch ( IOException e ) 
      {
		 System.err.println( "fails to create " + f_name );
      } catch ( ParseTreeException e ) 
      {
		 System.err.println( "errors during printing " + f_name );
		 e.printStackTrace();
      }
   }
}
