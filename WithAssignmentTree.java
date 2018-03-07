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
public class WithAssignmentTree extends AssignmentTree {
    WithStatement surroundingWithStatement;
    
    public WithAssignmentTree(TreePart theVar, TreePart theExpression, WithStatement outerWithStatement) {
        super(theVar, theExpression);
        assert outerWithStatement != null;
        surroundingWithStatement = outerWithStatement;
    }
    
    public String toJavaCode() {
        System.out.println("With Assignment");
        assert surroundingWithStatement != null;
        String id = surroundingWithStatement.getPrependIdentifier() + ".";
        String result = whereToPutResult.toJavaCode();
        result = id + result + " = " + expression.toJavaCode() + "; //With ASSIGNMENT\n";
        return result;
    }
    
}
