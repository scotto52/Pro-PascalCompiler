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
import java.util.ArrayList;

public class ProcedurePart extends Statement {
    
    protected String procedureName;
    protected BlockStatement myBlock;
    protected ArrayList<VariablePart> theVars = null;
    
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
    
    public void setTheVars(ArrayList<VariablePart> theVars) {
        this.theVars = theVars;
    }
    
    @Override public String toJavaCode() {
        assert procedureName != null;
        assert myBlock != null : "myBlock null blocks not allowed";
        String  code = super.toJavaCode() + "\n";          
        code = code + "//------------------------------------------------------";         
        code = code + "\n public static void "  + procedureName + "()";
        code = code + myBlock.toJavaCode() + "// END PROCEDURE " + procedureName + "\n";
        return  code ;
    }
}

