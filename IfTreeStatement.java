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
public class IfTreeStatement extends Statement {
    TreePart expression;
    Statement block;
    Statement eblock; //block to hold ELSE statements
    
    public IfTreeStatement(TreePart ex, Statement blk)
    {
        this.expression = ex;
        this.block = blk;
    }
    public IfTreeStatement(TreePart ex, Statement blk, Statement eblk)
    {
        this.expression = ex;
        this.block = blk;
        this.eblock = eblk;
    }
    @Override public String toJavaCode()
    {
        String result = "if("+ expression.toJavaCode() + ") \n { \n " +
                block.toJavaCode() + " \n} ";
        if(eblock != null)
        {
            result = result + "else \n { \n " +
                    eblock.toJavaCode() + " \n} ";
        }
        return result;
            
    }
}

