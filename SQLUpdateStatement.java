/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robertson
 */
public class SQLUpdateStatement extends SQLStatement {
    
    List<SQLUpdateAssignment> updates = new ArrayList<SQLUpdateAssignment>(5);
    String tableName;
    SQLStatement whereStatement;
    
    public SQLUpdateStatement(String tableName, List<SQLUpdateAssignment> theAssignments) {
        super();
        assert theAssignments != null;
        this.tableName = tableName;
        updates = theAssignments;
    }
    
    @Override public String toJavaCode() {
        assert tableName != null;
        String sql = "\"Update "+tableName+ " SET ";
        String allAssignments = "";
        for(SQLUpdateAssignment it: updates) {
            if(! allAssignments.isEmpty()) {
                allAssignments = allAssignments + " , ";
            }
            allAssignments = allAssignments + it.toJavaString();
        }
        sql = sql + allAssignments + "\"";
        String s = "\n //SQL UPDATE \n" +
                " assert gTheDatabaseConnection != null ; \n" + 
                " try \n" + 
                " { \n" +
                "  String sqlInject = HERE ; \n".replace("HERE", sql) +
                "  Statement stmt = gTheDatabaseConnection.createStatement();\n" + 
                "  stmt.executeUpdate(sqlInject);\n" + 
                "  stmt.close();\n" + 
                " } catch(SQLException e)\n" + 
                " {\n" + 
                "  System.out.println(e.getMessage());\n" +
                " }";
        return s;
    }
}
