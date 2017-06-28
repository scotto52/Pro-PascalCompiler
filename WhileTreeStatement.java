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
public class WhileTreeStatement extends Statement
{
    TreePart expression;
    Statement block;
    
    public WhileTreeStatement(TreePart ex, Statement blk)
    {
        this.expression = ex;
        this.block = blk;
    }
    @Override public String toJavaCode()
    {
        return "while( "+ expression.toJavaCode() + ") \n { \n " +
                block.toJavaCode() + " \n} ";
    }
}
