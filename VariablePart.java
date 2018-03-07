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
   TypePart whatIsMyType;
   
   public VariablePart( String theName ) 
     { 
        variablename  = theName.toLowerCase() ;
        whichPrimativeType = whichPrimativeType.INTEGER; //default to int
        defaultValue = 0;
     } 
   
   public String toJavaCode()
   { 
       return  variablename  ; 
   } 
   public String getDefinitionString()
   {
       //return "float" + variablename + " = 0.0; //default to 0.0\n" ;
       if(whatIsMyType != null)
       {
           return whatIsMyType.getName() +
                   " " + variablename + " " +
                   whatIsMyType.getJavaAllocationCode() + ";\n";
       }
       return VariableType.getJavaStringFor(whichPrimativeType)+
               " "+ variablename + " = " + defaultValue + ";\n";
   }
   public String getVariableName()
   {
       return variablename;
   }
   public VariableType getVariableType()
   {
       return whichPrimativeType;
   }
   public void setVariable(VariableType whichType)
   {
       this.whichPrimativeType = whichType;
   }
   public void setDefaultValue(double d)
   {
       defaultValue = d;
   }
   
   public VariableType getType() {
       
       return whichPrimativeType;
   }
   
   public void setType(TypePart whichType)
   {
       this.whatIsMyType = whichType;
   }
 } 