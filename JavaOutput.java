// GENERATED CODE 
package chapter2; 
import java.sql.*;
// PROGRAM TESTSQLUPDATE
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
int x  = 0 ;

// END VARS 
// BEGIN CODE

 //SQL UPDATE 
 assert gTheDatabaseConnection != null ; 
 try 
 { 
  String sqlInject = "Update myDatabase SET cop = " + x + " , thing = 23.0" ; 
  Statement stmt = gTheDatabaseConnection.createStatement();
  stmt.executeUpdate(sqlInject);
  stmt.close();
 } catch(SQLException e)
 {
  System.out.println(e.getMessage());
 }
gDisconnectToDatabase();
} // END BLOCK 
 
 } // END PROGRAM
