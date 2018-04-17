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
public class RecordPart extends TreePart {
    TreePart codeBeforeRecord;
    TreePart indexExpression;
    
    public RecordPart(TreePart head, TreePart submember) {
        assert indexExpression !=null : "null index expression. ";
        codeBeforeRecord = head;
        indexExpression = submember;
    } 
    
    public String toJavaCode() {
        return codeBeforeRecord.toJavaCode() + "." + indexExpression.toJavaCode();
    }
    
}

