////////////////////////////////////////////////////////////////////////////
// Module : IHI_Writer.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op;

import java.io.*;
import openjava.ptree.*;
import mujava.op.util.MutantCodeWriter;
import mujava.MutationSystem;

/**
 * <p>Output and log IHI mutants to files</p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class IHI_Writer extends MutantCodeWriter
{
   FieldDeclaration mutant = null;

   public void setMutant(FieldDeclaration mutant)
   {
      this.mutant = mutant;
   }

   public IHI_Writer( String file_name, PrintWriter out ) 
   {
	  super(file_name, out);
   }

   public void visit( ClassDeclaration p ) throws ParseTreeException
   {
	  if (p.getName().equals(MutationSystem.CLASS_NAME))
 	  {
         writeTab();

         /*ModifierList*/
         ModifierList modifs = p.getModifiers();
         if (modifs != null) 
         {
            modifs.accept( this );
            if (! modifs.isEmptyAsRegular())  
               out.print( " " );
         }

         /*"class"*/
         if (p.isInterface()) 
         {
            out.print( "interface " );
         } 
         else 
         {
            out.print( "class " );
         }

         String name = p.getName();
 		 out.print(name);

         /*"extends" TypeName*/
         TypeName[] zuper = p.getBaseclasses();
         if (zuper.length != 0)
         {
            out.print( " extends " );
            zuper[0].accept( this );
            for (int i = 1; i < zuper.length; ++i) 
            {
               out.print( ", " );
               zuper[i].accept( this );
            }
         } 
         else 
         {
        	// do nothing
         }

         /* "implements" ClassTypeList */
         TypeName[] impl = p.getInterfaces();
         if (impl.length != 0) 
         {
            out.print( " implements " );
            impl[0].accept( this );
            for (int i = 1; i < impl.length; ++i) 
            {
               out.print( ", " );
               impl[i].accept( this );
            }
         } 
         else 
         {
        	// do nothing
         }

         out.println(); 
         line_num++;

         /* MemberDeclarationList */
         MemberDeclarationList classbody = p.getBody();
         writeTab();  
         out.println( "{" ); 
         line_num++;
         
         if (classbody.isEmpty()) 
         {
            classbody.accept( this );
         } 
         else 
         {
            out.println(); line_num++;
            pushNest();
	        // -------------------------
	        mutated_line = line_num;
	        writeLog(removeNewline(mutant.toString())+" is added.");
    	    // -------------------------
	        mutant.accept(this);
	        out.println(); line_num++;
	        classbody.accept( this );  popNest();
            out.println(); line_num++;
         }
         writeTab(); 
         out.print( "}" );

         out.println(); 
         line_num++;
      }
   }
}
