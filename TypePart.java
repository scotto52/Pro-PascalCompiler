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
public class TypePart extends TreePart{
    String typeName;
    
    public String toString() {
        return this.toJavaCode();
    }
    
    public String getJavaAllocationCode() {
        assert false;
        return " // getJavaAllocationCode ";
    }
    
    public String toJavaCode()
    {
        return " toJavaCode for type not implemented";
    }

    public void setName(String name) {}
    

    
    public String getName() {
        return typeName;
    }

    
}

