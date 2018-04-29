// GENERATED CODE 
package propascal.transcompiler; 
import java.sql.*;
// PROGRAM EXAMPLE4
class JavaOutput 
{
   static Connection gTheDatabaseConnection = null; 
   static void gConnectTodatabase()
   { 
       try {
           Class.forName("org.sqlite.JDBC");
           gTheDatabaseConnection = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");
       }
       catch (Exception e) 
       {
           System.err.println(e.getClass().getName() + ": " + e.getMessage() ); 
           System.exit(0);
       }
       System.out.println("database successfully created");
   }

static void gDisconnectToDatabase()
{ 
 try 
  { 
   if(gTheDatabaseConnection!=null) gTheDatabaseConnection.close();
   gTheDatabaseConnection = null; 
  } catch (Exception e) 
  {
   System.err.println("DISCONNECT" + e.getClass().getName() + ": " + e.getMessage() );
   System.exit(0);
  }
  
  System.out.println("database successfully DISCONNECTED"); 
} 
public static void main(String[] args) 
{
 gConnectTodatabase();
// VARS 
int thiny  = 0 ;

// END VARS 
// BEGIN CODE
try {Statement stmt = gTheDatabaseConnection.createStatement();
 stmt.executeUpdate("CREATE TABLE KEITH  (WORKING INTEGER , KEEPIT REAL) "); // DO STATEMEMT
 stmt.close();} catch(SQLException e) {
 System.out.println(e.getMessage());
}


 //SQL UPDATE 
 assert gTheDatabaseConnection != null ; 
 try 
 { 
  String sqlInject = "Update KEITH SET WORKING = 1.0 WHERE ID = " + thiny + "" ; 
  Statement stmt = gTheDatabaseConnection.createStatement();
  stmt.executeUpdate(sqlInject);
  stmt.close();
 } catch(SQLException e)
 {
  System.out.println(e.getMessage());
 }

//SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }

System.out.println(23.0);/* WriteLn */ 
gDisconnectToDatabase();
} // END BLOCK 
 
 } // END PROGRAM
