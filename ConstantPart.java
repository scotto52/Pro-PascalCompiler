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
   ConstantType whichPrimativeType;
   double value;
   
   public ConstantPart( String theName ) 
     { 
        constantname  = theName.toLowerCase() ;
        whichPrimativeType = whichPrimativeType.INTEGER; //default to int
        value = 0;
     } 
   
   public String toJavaCode()
   { 
       return  "(CONST) "+constantname  ; 
   } 
   public String getDefinitionString()
   {
       //return "float" + variablename + " = 0.0; //default to 0.0\n" ;
       return ConstantType.getJavaStringFor(whichPrimativeType)+
               " "+ constantname + " = " + value + ";\n";
   }
   public String getConstantName()
   {
       return constantname;
   }
   public void setConstant(ConstantType whichType)
   {
       this.whichPrimativeType = whichType;
   }
   public void setValue(double d)
   {
       value = d;
   }
}
