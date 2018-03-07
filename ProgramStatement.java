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
public class ProgramStatement extends Statement{
    String name;
    BlockStatement blk;
    public ProgramStatement(String called, BlockStatement block)
    {
        this.name = called;
        this.blk = block;
    }
    static String header = "package chapter2 ; \n"+"import java.sql.*;\n"+
            "// PROGRAM $$PROGRAMNAME$$" + "\n" + "class JavaOutput \n{\n" +
            //"Connection gTheDatabaseConnection = null; \n" +
            "   static Connection gTheDatabaseConnection = null; \n" +
            "   static void gConnectTodatabase()\n" +
            "   { \n" + 
            "       try {\n" + 
            "           Class.forName(\"org.sqlite.JDBC\");\n" +
            "           gTheDatabaseConnection = DriverManager.getConnection(\"jdbc:sqlite:SqliteJavaDB.db\");\n" +
            "       }\n" + 
            "       catch (Exception e) \n" +
            "       {\n" +
            "           System.err.println(e.getClass().getName() + \": \" + e.getMessage() ); \n" +
            "           System.exit(0);\n" +
            "       }\n" +
            "       System.out.println(\"database successfully created\");\n" +
            "   }\n";
    @Override public String toJavaCode()
    {
        return header.replaceFirst("$$PROGRAMNAME$$", name) + blk.toJavaCode(true) + " \n } // END PROGRAM\n";
    }
}
