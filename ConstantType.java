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
public enum ConstantType {
    INTEGER, REAL, STRING, CHARACTER, BOOLEAN, ERROR ; //KEY STUFF
    
    private static String builtInTokens[] =
    {
        "INTEGER",
        "REAL",
        "STRING",
        "CHARACTER",
        "BOOLEAN",
        "ERROR"
    };
    private static String builtInTokensForJava[] =
    {
        "int",
        "float",
        "string",
        "char",
        "boolean",
        "ERROR.."
    };
    
    public static String getJavaStringFor (ConstantType t)
    {
        int index = 0;
        for(ConstantType c: ConstantType.values() )
        {
            if(c==t)
            {
                return builtInTokensForJava[index];
            }
            index++;
        }
        return "ERROR";
    }
    
    public static String getPascalStringFor(ConstantType t)
    {
        int index = 0;
        for(ConstantType c: ConstantType.values() )
        {
            if(c==t)
            {
                return builtInTokens[index];
            }
            index++;
        }
        return "ERROR";
    }
    
    public static ConstantType getEnumerationForString(String name)
    {
        int index = 0;
        for(ConstantType c: ConstantType.values())
        {
            if(name.equalsIgnoreCase(builtInTokens[index])==true)
            {
                return c;
            }
            index++;
        }
        return ConstantType.ERROR;
    }
    
};
