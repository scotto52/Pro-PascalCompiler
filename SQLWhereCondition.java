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
public class SQLWhereCondition extends SQLUpdateAssignment {
    
    public SQLWhereCondition(String sqlVarName, int isNumberOrVariable) {
        
        super(sqlVarName, isNumberOrVariable);
    }
    
    public SQLWhereCondition(String sqlVarName, int isNumberOrVariable, double value) {
        
        super(sqlVarName, isNumberOrVariable, value);
    }
    
    public SQLWhereCondition(String sqlVarName, VariablePart varName) {
        
        super(sqlVarName, varName);
    }
    
    public SQLWhereCondition(String sqlVarName, String asThisText) {
        
        super(sqlVarName, asThisText);
    }
    
}

