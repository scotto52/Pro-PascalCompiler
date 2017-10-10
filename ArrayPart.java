package chapter2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robertson
 */
public class ArrayPart extends TreePart {
    TreePart codeBeforeArray;
    TreePart indexExpression;
    
    public ArrayPart(TreePart precedingArrayTreePart, TreePart indexExpression)
    {
        assert indexExpression !=null : "null index expression. ";
        this.codeBeforeArray = precedingArrayTreePart;
        this.indexExpression = indexExpression;
    }
    
    public void setIndexExpression(TreePart tp)
    {
        this.indexExpression = tp;
    }
    
    public String toString(){ return this.toJavaCode();}
    public String toJavaCode()
    {
        assert indexExpression != null : "null index expression. ";
        return codeBeforeArray.toJavaCode() + 
                "[ " + indexExpression.toJavaCode() + " ]";
    }
    
    public VariableType getType()
    {
        return codeBeforeArray.getType();
    }
    
}
