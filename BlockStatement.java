
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
import java.util.ArrayList;
import java.util.*;



public class BlockStatement extends Statement
{
    HashMap<String, ConstantPart> blockConstantTable = new HashMap<String, ConstantPart>();
    // The block constant table has all the constants declared in the block
    HashMap<String,VariablePart> blockSymbolTable = new HashMap<String, VariablePart>();
    ArrayList<Statement> mySteps;
    // The block symbol table has all the variables declared in the block
    public BlockStatement()
    {
        mySteps = new ArrayList<Statement>();
    }
    public void add (Statement s)
    {
        mySteps.add(s);
    }
    @Override
    public String toJavaCode()
    {
        StringBuffer b = new StringBuffer("{ // BEGIN \n");
        // NOW PUT CONSTANTS HERE
        b.append("// CONSTS \n");
        for(String con : blockConstantTable.keySet())
        {
            ConstantPart t = blockConstantTable.get(con);
            b.append(t.getDefinitionString());
        }
        
        // NOW PUT LOCAL VARS HERE
        b.append("// VARS \n");
        for(String var : blockSymbolTable.keySet())
        {
            VariablePart t = blockSymbolTable.get(var);
            b.append(t.getDefinitionString());
        }
        
        for (Statement s: mySteps)
        {
            b.append(s.toJavaCode());
            b.append('\n');
        }
        b.append("} // END \n");
        return b.toString();
    }
    
    public boolean addVariable(VariablePart it)
    {
        boolean result = true;
        VariablePart already = blockSymbolTable.get(it.getVariableName() );
        if (already != null)
        {
            result = false;
            System.out.println("WARNING DUPLICATE VARIABLE NAME  " + it.getVariableName() );
        }
        blockSymbolTable.put(it.getVariableName(), it );
        return result;
    }
    
    public boolean addConstant(ConstantPart it)
    {
        boolean result = true;
        ConstantPart already = blockConstantTable.get(it.getConstantName());
        if (already !=null)
        {
            result = false;
            System.out.println("WARNING DUPLICATE CONSTANT NAME " + it.getConstantName());
        }
        blockConstantTable.put(it.getConstantName(), it );
        return result;
    }
    
}
