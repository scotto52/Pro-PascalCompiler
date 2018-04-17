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
public class WithStatement extends Statement {
    TreePart recordIdentifier;
    Statement theStatements;
    
    public WithStatement(TreePart ident) {
        this.recordIdentifier = ident;
        this.theStatements = null;
    }
    
    public void setStatements(Statement stmt) {
        this.theStatements = stmt;
    }
    
    public String getPrependIdentifier() {
        return recordIdentifier.toJavaCode();
    }
    
    @Override public String toJavaCode() {
        return theStatements.toJavaCode();
    }
}

