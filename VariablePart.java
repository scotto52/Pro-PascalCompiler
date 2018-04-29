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
