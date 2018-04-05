
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
    Map<String,VariablePart> blockSymbolTable = new HashMap<>();
    ArrayList<Statement> mySteps;
    Map<String,TypePart> typeSymbolTable = new HashMap<>();
    BlockStatement parentBlock;
    Map<String,ProcedurePart> procSymbolTable = new HashMap<>();
    
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
        return this.toJavaCode(false);
    }
    public String toJavaCode(boolean isTop)
    {
        StringBuffer b = new StringBuffer(" \n");
        // NOW PUT LABELS HERE
        if(!this.blockLabelSet.isEmpty()) 
        {
            b.append("// LABELS \n");
            for (LabelPart temp : blockLabelSet)
            {
                b.append(temp.toJavaCode());
                b.append('\n');
            }
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
        if(! procSymbolTable.isEmpty())
        {
            b.append(("//PROCEDURES  \n"));
            for(ProcedurePart proc : procSymbolTable.values()) 
            {
                b.append(proc.toJavaCode());
            }
        }
        if(isTop)
            b.append("public static void main(String[] args) \n{\n gConnectTodatabase();\n");
        else
            b.append("{\n // BLOCK BEGINING ");
        // NOW PUT VARS HERE
        if(!blockSymbolTable.isEmpty())
        {
            b.append("// VARS \n");
            for(String var : blockSymbolTable.keySet())
            {
                VariablePart t = blockSymbolTable.get(var);
                b.append(t.getDefinitionString());
                b.append('\n');
            }
            b.append("// END VARS \n");
        }
        b.append("// BEGIN CODE\n");
        for (Statement s: mySteps)
        {
            System.out.println("--------STATEMENT-------");
            assert s != null : " Added null statement";
            System.out.println(">" + s);
            assert s.toJavaCode()!=null: "WEIRDNESS";
            b.append(s.toJavaCode());
            b.append('\n');
        }
        if(isTop)b.append("gDisconnectToDatabase();\n");
        b.append("} // END BLOCK \n");
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
    
    public VariablePart getVarForName(String name) {
        if(parentBlock != null) //try parent first
        {
            VariablePart it = parentBlock.getVarForName(name);
            if(it != null)return it;
        }
        return blockSymbolTable.get(name);
    }
    
    public boolean addLabel(LabelPart it) {
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
    
    public boolean addType(String id, TypePart it) {
        id = id.toLowerCase(); //remove case
        boolean result = typeSymbolTable.containsKey(id);
        if (result != false)
        {
            System.out.println("WARNING DUPLICATE TYPE NAME  " + id );
        }
        typeSymbolTable.put(id, it );
        System.out.println("Record " + id + " added to list of Types.");
        return result;
    }
       
    public TypePart getType (String name) {
       return typeSymbolTable.get(name.toLowerCase());
    }
        
    public ProcedurePart getProcedureFor(String name) {
        name = name.toLowerCase();
        System.out.println("SR" + name);
        ProcedurePart proc = procSymbolTable.get(name);
        if(proc == null && parentBlock != null) {
            proc = parentBlock.getProcedureFor(name);
        }
        return proc;
    }    
}
