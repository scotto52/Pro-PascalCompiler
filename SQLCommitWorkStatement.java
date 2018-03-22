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
public class SQLCommitWorkStatement extends SQLStatement {
    
    public SQLCommitWorkStatement() {
        
        super();
    }
    
    @Override public String toJavaCode() {
        
        return super.toJavaCode() + "\n//SQLCommitWorkStatement" + 
                "assert gTheDatabaseConnection != null ; \n" + 
                "try \n" + 
                " {\n" + 
                "   gTheDatabaseConnection.commit();\n" + 
                " } catch(SQLException e)\n" + 
                " {\n" + 
                "   System.out.println(e.getMessage());\n" + 
                " }\n";
    }
}
