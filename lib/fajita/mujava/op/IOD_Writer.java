////////////////////////////////////////////////////////////////////////////
// Module : IOD_Writer.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op;

import java.io.*;
import openjava.ptree.*;
import mujava.op.util.MutantCodeWriter;

/**
 * <p>Output and log IOD mutants</p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class IOD_Writer extends MutantCodeWriter
{
   MethodDeclaration mutant = null;

   public void setMutant(MethodDeclaration mutant)
   {
      this.mutant = mutant;
   }

   public IOD_Writer( String file_name, PrintWriter out ) 
   {
	  super(file_name, out);
   }

   /**
    * Write and log mutated line to files
    */
   public void visit( MethodDeclaration p ) throws ParseTreeException
   {
      if (!(isSameObject(p, mutant)))
      {
         super.visit(p);
      }
      else
      {
         // -----------------------------------------------------
         mutated_line = line_num;
         String temp= mutant.getModifiers().toString() + " "
			  + mutant.getReturnType().getName()+ " "
		      + mutant.getName() +"("
			  + mutant.getParameters().toString()+")";
         writeLog(removeNewline(temp)+" is deleted.");
         // ----------------------------------------------------
         writeTab();
         out.print("// ");

         /*ModifierList*/
         ModifierList modifs = p.getModifiers();
         if (modifs != null) 
         {
            modifs.accept( this );
            if (! modifs.isEmptyAsRegular())  
               out.print( " " );
         }

         TypeName ts = p.getReturnType();
         ts.accept( this );

         out.print( " " );

         String name = p.getName();
         out.print( name );

         ParameterList params = p.getParameters();
         out.print( "(" );
         if (! params.isEmpty()) 
         {
            out.print( " " );  
            params.accept( this );  
            out.print( " " );
         } 
         else 
         {
            params.accept( this );
         }
         out.print( ")" );

         TypeName[] tnl = p.getThrows();
         if (tnl.length != 0) 
         {
            out.println(); 
            line_num++;
            writeTab();  
            writeTab();
            out.print( "// throws " );
            tnl[0].accept( this );
            for (int i = 1; i < tnl.length; ++i) 
            {
               out.print ( ", " );
               tnl[i].accept( this );
            }
         }
         out.println("{ ... }");  
         line_num++;
      }
   }
}
