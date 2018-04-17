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

public class ProcedureCallStatement extends Statement {
    
    ProcedurePart myProcedure;
    ArrayList<TreePart> parameters = new ArrayList<>();
    
    ProcedureCallStatement(ProcedurePart ofThis) {
        super();
        myProcedure = ofThis;
    }
    
    public void addParameter(TreePart param) {
        parameters.add(param);
    }
    
    @Override
    public String toJavaCode() {
        String s = super.toJavaCode();
        s = "\n" + myProcedure.getProcedureName();
        if(parameters.size() == 0 ) {
            s = s + "(); // pascal procedure \n";
        }
        else // add parameters to procedure
        {
            s = s + "(";
            boolean first = true;
            for(TreePart exp : parameters)
            {
                if(first == false) s = s + ", ";
                s = s + exp.toJavaCode();
                first = false;
            }
            s = s + "); // pascal procedure \n";
        }
        return s;
    }
}

