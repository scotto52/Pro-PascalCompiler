
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
    HashSet<LabelPart> blockLabelSet = new HashSet<LabelPart>();
    // The block constant table has all the constants declared in the block
    Map<String,VariablePart> blockSymbolTable = new HashMap<>();
    ArrayList<Statement> mySteps;
    Map<String,TypePart> typeSymbolTable = new HashMap<>();
    // The block symbol table has all the variables declared in the block
    BlockStatement parentBlock;
    
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
        // NOW PUT LABELS HERE
        b.append("// LABELS \n");
        for (LabelPart temp : blockLabelSet)
        {
            b.append(temp.toJavaCode());
            b.append('\n');
        }
        
        if(!this.typeSymbolTable.isEmpty())
        {
            b.append("// TYPES");
            for(String tname : this.typeSymbolTable.keySet())
            {
                TypePart t = this.typeSymbolTable.get(tname);
                b.append(t.toJavaCode());
            }
        }
        // NOW PUT VARS HERE
        if(!blockSymbolTable.isEmpty())
        {
            b.append("// VARS \n");
            for(String var : blockSymbolTable.keySet())
            {
                VariablePart t = blockSymbolTable.get(var);
                b.append(t.getDefinitionString());
            }
            b.append("// END VARS \n");
        }
        
        b.append("// BEGIN\n");
        for (Statement s: mySteps)
        {
            System.out.println("--------STATEMENT-------");
            assert s != null : " Added null statement";
            System.out.println(">" + s);
            assert s.toJavaCode()!=null: "WEIRDNESS";
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
    
    public boolean addLabel(LabelPart it)
    {
        boolean result = true;
        LabelPart lab = it;
        boolean isIn = blockLabelSet.contains(lab);
        if(isIn == true)
          {
              result = false;
              System.out.println("WARNING DUPLICATE LABEL NAME  " + it.getLabelName() );
          }
          blockLabelSet.add(lab);
          return result;
    }
    
        public boolean addType(String id, TypePart it)
    {
        boolean result = typeSymbolTable.containsKey(id);
        if (result = false)
        {
            System.out.println("WARNING DUPLICATE TYPE NAME  " + id );
        }
        typeSymbolTable.put(id, it );
        return result;
    }
    
    /*public boolean addConstant(ConstantPart it)
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
    }*/
    
}
