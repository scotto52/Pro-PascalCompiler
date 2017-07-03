/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;

/**
 *
 * @author Robertson
 */
public class ConstantPart extends TreePart {
   String constantname ; // For now will change later.
   double value;
   
   public ConstantPart( String theName ) 
     { 
        constantname  = theName.toLowerCase() ;
        value = 0;
     } 
   
   public String toJavaCode()
   { 
       return  "(CONST) "+constantname  ; 
   } 
   public String getDefinitionString()
   {
       return constantname + " = " + value + ";\n";
   }
   public String getConstantName()
   {
       return constantname;
   }

   public void setValue(double d)
   {
       value = d;
   }
}
