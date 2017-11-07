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
public class AssignmentTree extends Statement {
    
    TreePart whereToPutResult;
    TreePart expression;   
    public AssignmentTree (TreePart theVar, TreePart theExpression)
    {
        super();
        assert theExpression != null : "No null expressions";
        assert theVar != null:  "No null variable declarations";
        
        this.whereToPutResult = theVar;
        this.expression = theExpression;
    }
    public String toJavaCode()
    {
        String result = null;
        result = whereToPutResult.toJavaCode();
        result = result + " = " + expression.toJavaCode()+";//ASSIGNMENT \n";
        return result;
    }

}
