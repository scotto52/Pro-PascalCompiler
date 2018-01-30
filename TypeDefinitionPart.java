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
public class TypeDefinitionPart extends TreePart {
    
    TypePart theDefinition;
    String identifier;
    
    public TypeDefinitionPart(String ident, TypePart definition) {
        
        this.theDefinition = definition;
        this.identifier = ident;
        assert ident != null; //ensure type has a name
    }
    
    public String toString() {
        
        return " /* TYPE DEFINITION */ ";
    }
    
    public String getTypeName()
   {
       return identifier;
   }
}
