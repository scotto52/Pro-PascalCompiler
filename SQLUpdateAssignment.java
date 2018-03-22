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
public class SQLUpdateAssignment extends SQLStatement{
    
    public final static int kNULL = 0;
    public final static int kLiteralNumber = 1;
    public final static int kLiteralString = 2;
    public final static int kVariable = 3;
    
    double valueAsFloat = Double.NaN;
    String valueAsString = null;
    VariablePart valueAsVariable = null;
    
    int contents = kNULL; // NULL;
    
    String sqlVariablename;
    
    public SQLUpdateAssignment(String sqlVarName, int isNumberOrVariable, double value) {
        assert kLiteralNumber == isNumberOrVariable;
        sqlVariablename = sqlVarName;
        contents = kLiteralNumber;
        valueAsFloat = value;
    }
    
    public SQLUpdateAssignment(String sqlVarName, int isNumberOrVariable) {
        
        assert isNumberOrVariable == kNULL;
        sqlVariablename = sqlVarName;
        contents = kNULL;
        
    }
    
    public SQLUpdateAssignment(String sqlVarName, String asThisText) {
        
        contents = kLiteralString;
        assert asThisText != null;
        valueAsString = asThisText;
        sqlVariablename = sqlVarName;
    }
    
    public SQLUpdateAssignment(String sqlVarName, VariablePart LocVar) {
        
        contents = kVariable;
        assert LocVar != null;
        sqlVariablename = sqlVarName;
        valueAsVariable = LocVar;
    }
    
    public String toJavaString() {
        switch(contents) {
            case kLiteralNumber: {
                return this.sqlVariablename + " = " + valueAsFloat;
            }
            case kLiteralString:
            {
                return this.sqlVariablename + " = \'" + valueAsString + "\'";
            }
            case kNULL:
            {
                return this.sqlVariablename + " = NULL ";
            }
            case kVariable:
            {
                return this.sqlVariablename + " = \" + " + this.valueAsVariable +
                       " + \"";
            }
            default : {
                return "SQLUpdateAssignment MALFUNCTION " + kVariable;
            }
        }
    }
}
