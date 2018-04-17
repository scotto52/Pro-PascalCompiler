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
public class BuiltInType extends TypePart {
    
        VariableType whichPrimativeType = VariableType.INTEGER;
        
        public BuiltInType(VariableType builtInTypeName)
        {
            whichPrimativeType = builtInTypeName;
        }
        
        public String getJavaAllocationCode() {
            return " = 0 ";
        }
        
        public String getName() {
            return VariableType.getJavaStringFor(whichPrimativeType);
        }

        public String toJavaCode()
        {
            return VariableType.getJavaStringFor(whichPrimativeType);
        }    
}

