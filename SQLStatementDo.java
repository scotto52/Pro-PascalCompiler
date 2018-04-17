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
public class SQLStatementDo extends SQLStatement {
    
    String doStatement;
    
    public SQLStatementDo(String toDo) {
        this.doStatement = toDo;
    }
  
    @Override public String toJavaCode() {
        return
                "try {"
                + "Statement stmt = gTheDatabaseConnection.createStatement();\n" + 
                " stmt.executeUpdate(\"" + doStatement + "\"); // DO STATEMEMT\n" + 
                " stmt.close();" + 
                "} " +
                "catch(SQLException e) {\n" + 
                " System.out.println(e.getMessage());\n" + 
                "}\n";
    }
}

