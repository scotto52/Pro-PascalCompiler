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
public class WriteLnTree extends Statement {
    
    TreePart expression;
    
    public WriteLnTree(TreePart theExpression)
    {
        this.expression = theExpression;
    }
    @Override public String toJavaCode()
    {
        String result = "System.out.println( " ;
        result = result + expression.toJavaCode() + 
                ");/* WriteLn */ " + super.toJavaCode();
        return result;
    }
}
