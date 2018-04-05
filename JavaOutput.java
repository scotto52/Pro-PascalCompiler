// GENERATED CODE 
package chapter2; 
import java.sql.*;
// PROGRAM TESTPROCEDUREDO
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
// BEGIN CODE
System.out.println(23.0);/* WriteLn */ 
gDisconnectToDatabase();
} // END BLOCK 
 
 } // END PROGRAM
