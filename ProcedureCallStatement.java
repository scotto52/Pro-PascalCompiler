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
public class ProcedureCallStatement extends Statement {
    
    ProcedurePart myProcedure;
    
    ProcedureCallStatement(ProcedurePart ofThis) {
        super();
        myProcedure = ofThis;
    }
    @Override
    public String toJavaCode() {
        String s = super.toJavaCode();
        s = "\n" + myProcedure.getProcedureName() + "(); //pascal procedure \n";
        return s;
    }
}
