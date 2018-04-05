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
public class ProcedurePart extends Statement {
    
    protected String procedureName;
    protected BlockStatement myBlock;
    
    public ProcedurePart () {
        super();
    }
    
    public String getProcedureName() {
        return procedureName;
    }
    
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }
    
    public BlockStatement getMyBlock() {
        return myBlock;
    }
    
    public void setMyBlock(BlockStatement myBlock) {
        this.myBlock = myBlock;
    }
    
    @Override public String toJavaCode() {
        assert procedureName != null;
        assert myBlock != null : "myBlock mull blocks not allowed";
        String code = "\n //PROCEDURE \n";
        code = code + super.toJavaCode() + "\n";
        code = code + "public static void " + procedureName + "()";
        code = code + myBlock.toJavaCode() + "// END PROCEDURE " + procedureName + "\n";
        return  code ;
    }
}
