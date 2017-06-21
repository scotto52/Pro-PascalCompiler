/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;

/**
 *
 * @author nd2563
 */
//============================================================
 class VariablePart extends  TreePart 
 { 
   String variablename ; // For now will change later.
   VariableType whichPrimativeType;
   double defaultValue;
   
   public VariablePart( String theName ) 
     { 
        variablename  = theName.toLowerCase() ;
        whichPrimativeType = whichPrimativeType.INTEGER; //default to int
        defaultValue = 0;
     } 
   
   public String toJavaCode()
   { 
       return  "(VAR) "+variablename  ; 
   } 
   public String getDefinitionString()
   {
       //return "float" + variablename + " = 0.0; //default to 0.0\n" ;
       return VariableType.getJavaStringFor(whichPrimativeType)+
               " "+ variablename + " = " + defaultValue + ";\n";
   }
   public String getVariableName()
   {
       return variablename;
   }
   public void setVariable(VariableType whichType)
   {
       this.whichPrimativeType = whichType;
   }
   public void setDefaultValue(double d)
   {
       defaultValue = d;
   }
 } 