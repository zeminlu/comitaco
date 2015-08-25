////////////////////////////////////////////////////////////////////////////
// Module : AOR_Writer.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op;

import mujava.op.util.MutantCodeWriter;
import openjava.ptree.*;

import java.io.*;

/**
 * <p>Output and log AOR mutants to files</p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */ 

public class AOR_Writer extends MutantCodeWriter
{
   BinaryExpression original;
   int mutant_op;
 
   public AOR_Writer( String file_name, PrintWriter out ) 
   {
      super(file_name, out);
   }

   /**
    * Set original source code and mutated code
    * @param exp
    * @param op
    */
   public void setMutant(BinaryExpression exp, int op)
   {
      original = exp;
      mutant_op = op;
   }

   /**
    * Log mutated line
    */
   public void visit( BinaryExpression p ) throws ParseTreeException
   {
      if (isSameObject(p, original))
      {
    	 BinaryExpression mutant_exp;
    	 mutant_exp = (BinaryExpression)original.makeRecursiveCopy();
    	 mutant_exp.setOperator(mutant_op);
    	 super.visit(mutant_exp);

     	 String operator = mutant_exp.operatorString();
    	 out.print( " " + operator + " " );
	     // -----------------------------------------------------------
	     mutated_line = line_num;
	     String log_str = p.operatorString()+ " => " + operator;
	     writeLog(removeNewline(log_str));
	     // -------------------------------------------------------------

     	 mutant_exp = null;
      }
      else
      {
         super.visit(p);
      }
   }
}
