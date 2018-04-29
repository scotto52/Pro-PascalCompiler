/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propascal.transcompiler;

/**
 *
 * @author Robertson
 */
public class ConstantPart extends VariablePart {
   //String constantname ; // For now will change later.
   //double value;
   
   public ConstantPart( String theName ) 
     { 
        //constantname  = theName.toLowerCase() ;
        //value = 0;
         super(theName);
     } 
   
   /** public String toJavaCode()
   { 
       return  "(CONST) "+constantname  ; 
   } */
   public String getDefinitionString()
   {
       return "static " + VariableType.getJavaStringFor(whichPrimativeType) + 
               " " + variablename + " = " + defaultValue + ";\n";
   }
   /** public String getConstantName()
   {
       return constantname;
   }

   public void setValue(double d)
   {
       value = d;
   } */
}

