////////////////////////////////////////////////////////////////////////////
// Module : OMR_Writer.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op;

import java.io.*;
import openjava.ptree.*;
import mujava.op.util.MutantCodeWriter;

/**
 * <p>Output and log OMR mutants to files</p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class OMR_Writer extends MutantCodeWriter
{
   MethodDeclaration original = null;
   String mutant = null;
   boolean flag = false;
   boolean isVoid = true;
   
   /**
    * Set original source code and mutated code
    * @param original
    * @param mutant
    */
   public void setMutant(MethodDeclaration original, String mutant)
   {
      this.mutant = mutant;
      this.original = original;
   }

   public OMR_Writer( String file_name, PrintWriter out ) 
   {
	  super(file_name,out);
   }

   public void visit( StatementList p ) throws ParseTreeException
   {
      if (!flag)
      {
		 super.visit(p);
      }
      else
      {
		 //-------------------------------------------------------
		 mutated_line = line_num;
		 String temp_str = original.getName() + "(";
		 ParameterList pl = original.getParameters();
		 
		 for (int i=0; i<pl.size()-1; i++)
		 {
			temp_str = temp_str + pl.get(i).getVariable() + ",";
		 }
		 temp_str = temp_str+pl.get(pl.size()-1).getVariable()+")";
		 writeLog(removeNewline(temp_str+" => "+mutant));
		 //----------------------------------------------------------
		 
		 writeTab();
		 if (isVoid)
		 {
		    out.println(mutant);
		 }
		 else
		 {
		    out.println("return " + mutant);
		 }
		 line_num++;
		 flag = false;
      }
   }

    public void visit( MethodDeclaration p ) throws ParseTreeException
    {
      if(isSameObject(p, original))
      {
		 flag = true;
		 if (!p.getReturnType().toString().equals("void")) 
			isVoid = false;
      }
      super.visit(p);
    }

}
