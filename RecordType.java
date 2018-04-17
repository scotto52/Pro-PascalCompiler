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

import java.util.HashMap;
import java.util.Map;

public class RecordType extends TypePart {
    
    HashMap<String, TypePart> myFields;
    String recordName;
    
    public RecordType() {
        this.recordName = null;
        myFields = new HashMap<String, TypePart>();
    }
    
    public void setName(String name) {
        recordName = name;
    }
    
    public void addType (String variableName, TypePart p) {
        assert variableName != null;
        assert p != null;
        myFields.put(variableName, p);
    }
    
    public boolean fieldExists (String name) {
        return myFields.containsKey(name);
    }
    
    public String toJavaCode()
    {
        String result = "//-------------------------------\n static class ";
        if(recordName == null)
        {
            result = result + " CRecord" + myFields.hashCode() + " \n{\n ";
        } else
        {
            result = result + " " + recordName + " \n{\n ";
        }
        for (String fieldName: myFields.keySet())
        {
            result = result + " " +
                    myFields.get(fieldName).toJavaCode() + " " +
                    fieldName + ";\n";
        }
        result += "}; //END OF CLASS \n";
        return result;
    
    }
    
    public String getJavaAllocationCode() {
        return " = new " + getName() + "(); //simulate stack allocation " ;
    }
    
    public String getName() {
        return recordName;
    }
    
}


