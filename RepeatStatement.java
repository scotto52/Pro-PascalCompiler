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
public class RepeatStatement extends BlockStatement{
    BlockStatement block;
    TreePart expression;
    
    public RepeatStatement(BlockStatement blk, TreePart exp)
    {
        this.block = blk;
        this.expression = exp;
    }
    @Override public String toJavaCode()
    {
        return "do { "+ block.toJavaCode() + "} \n while (" +
                expression.toJavaCode() + ") \n";
    }
    
}
