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
public class ProgramStatement extends Statement{
    String name;
    Statement blk;
    public ProgramStatement(String called, Statement block)
    {
        this.name = called;
        this.blk = block;
    }
    @Override public String toJavaCode()
    {
        return "\n// PROGRAM " +name + "\n" + blk.toJavaCode();
    }
    
}
