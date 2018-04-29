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
public enum VariableType {
    INTEGER, REAL, STRING, CHARACTER, BOOLEAN, ERROR ; //KEY STUFF
    
    private static String builtInTokens[] =
    {
        "INTEGER",
        "REAL",
        "STRING",
        "CHAR",
        "BOOLEAN",
        "ERROR"
    };
    private static String builtInTokensForJava[] =
    {
        "int",
        "double",
        "string",
        "char",
        "boolean",
        "ERROR.."
    };
    
    public static String getJavaStringFor (VariableType t)
    {
        int index = 0;
        for(VariableType v: VariableType.values() )
        {
            if(v==t)
            {
                return builtInTokensForJava[index];
            }
            index++;
        }
        return "ERROR";
    }
    
    public static String getPascalStringFor(VariableType t)
    {
        int index = 0;
        for(VariableType v: VariableType.values() )
        {
            if(v==t)
            {
                return builtInTokens[index];
            }
            index++;
        }
        return "ERROR";
    }
    
    public static VariableType getEnumerationForString(String name)
    {
        int index = 0;
        for(VariableType v: VariableType.values())
        {
            if(name.equalsIgnoreCase(builtInTokens[index])==true)
            {
                return v;
            }
            index++;
        }
        return VariableType.ERROR;
    }
    
};

