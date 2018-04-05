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
public class SQLWhereStatement extends SQLStatement {
    
    SQLWhereCondition theCondition;
    
    public SQLWhereStatement (SQLWhereCondition whereExpression) {
        
        super();
        theCondition = whereExpression;
    }
    @Override
    public String toJavaCode() {
        
        assert theCondition != null;
        
        return " WHERE " + theCondition.toJavaString();
    }
}
