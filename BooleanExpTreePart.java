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
public class BooleanExpTreePart extends TreePart{
    public static final int GREATER_THAN = 0;
    public static final int LESS_THAN = 1;
    public static final int EQUAL_TO = 2;
    public static final int NOT_EQUAL = 3;
    public static final int GREATER_OR_EQUAL = 4;
    public static final int LESSTHAN_OR_EQUAL = 5;
    public static final int AND = 6;
    public static final int OR = 7;
    public static final int NOT = 8;
    
    TreePart before;
    TreePart after;
    int symbol;
    
    public BooleanExpTreePart(TreePart fore, int action, TreePart after)
    {
        this.before = fore;
        this.symbol = action;
        this.after = after;
    }
    
    public String toJavaCode()
    {
        if(symbol == NOT)
        {
            return " ! ( " + before.toJavaCode() + " ) ";
        }
        if(symbol == AND)
        {
            return " ( ( " + before.toJavaCode() +
                    " ) && ( " + 
                    after.toJavaCode() + 
                    " ) ) ";
        }
        if(symbol == OR)
        {
            return " ( ( " + before.toJavaCode() +
                    " ) || ( " +
                    after.toJavaCode() +
                    " ) ) ";
        }
        return "(" + before.toJavaCode() + " " + valuesJava[ symbol] +
                " " + after.toJavaCode() + ")";
    }
    
    public VariableType getType()
    {
        return VariableType.BOOLEAN;
    }
    
    String valuesJava[] = 
    { // these are in the same order as the static numbers.
        ">", "<", "==", "!=", ">=", "<=", "AND", "OR", "NOT"
    };
    // these arn't used really.
    String valuesPascal[] = 
    {
         ">", "<", "==", "!=", ">=", "<=", "AND", "OR", "NOT"
    };
    
    public String[] getPascalValues()
    {
        return valuesPascal;
    }
}
