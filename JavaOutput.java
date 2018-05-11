// GENERATED CODE 
package propascal.transcompiler; 
import java.sql.*;
// PROGRAM ROD
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
// TYPES//-------------------------------
 static class  IoStatus 
 {
  int DeviceInfo;
  double IoStat;
 }; //END OF CLASS 
//-------------------------------
 static class  ITEMLIST 
 {
  double BufLen;
  int Terminator;
  int BufAddr;
 }; //END OF CLASS 
//-------------------------------
 static class  QUADWORD 
 {
  int Hi;
  int Lo;
 }; //END OF CLASS 
// VARS 
char rslinkstatus;
char banchan;
char bobnow;
double delaytime;
int elementsmonitored;
char cntnrfuzz;
static int linkfailure = 4;
char fuzztypee;
char uid;
ITEMLIST trnlist  = new ITEMLIST(); //simulate stack allocation ;
char eqivnamelen;
int sqllocation;
char odate;
char banmsg;
int sysstatus;
int sqlcode;
boolean linkfault;
char tubtinnos  = new char[24];
boolean debug;
char logname;
static int linkhealthy = 3;
int count;
int messagelength;
char tubtinno;
char bobnoe;
static int nodata = 1403;
char bobcntnr;
int eqivname  = new int[4];
static int banmsglen = 150;
IoStatus statblock  = new IoStatus(); //simulate stack allocation ;
static int failure = 0;
static int healthy = 1;
int linkstatus;
boolean forever;
char pwd;
char fuzztypew;
// END VARS 

/*  ******PROCEDURES ****** */

//------------------------------------------------------
 public static void TestLink() 
{
 // BLOCK BEGINING // BEGIN CODE
 if(debug) 
 { 
  System.out.println(36);/* WriteLn */  
 } 
 if((linkstatus == healthy)) 
 { 
 if((rslinkstatus == 37)) 
 { 
  banmsg = linkfailure;//ASSIGNMENT 
  SendBannerMessage(); // pascal procedure 
  linkstatus = failure;//ASSIGNMENT 
 } else 
 { 
 if((rslinkstatus == 38)) 
 { 
  sqllocation = 350;//ASSIGNMENT 
  //SQL UPDATE 
  assert gTheDatabaseConnection != null ; 
  try 
  { 
   String sqlInject = "Update KEITH SET LKSTAT = 39.0" ; 
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
 }  
 } 
 } else 
 { 
  if((linkstatus == failure)) 
 { 
 if((rslinkstatus == 40)) 
 { 
  banmsg = linkhealthy;//ASSIGNMENT 
  SendBannerMessage(); // pascal procedure 
  sqllocation = 370;//ASSIGNMENT 
  //SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
 try 
  {
    gTheDatabaseConnection.commit();
  } catch(SQLException e)
  {
    System.out.println(e.getMessage());
  }
  linkstatus = healthy;//ASSIGNMENT 
 } 
 }  
 } 
} // END BLOCK 
// END PROCEDURE TestLink

//------------------------------------------------------
 public static void TranslateStop()  
{
 // BLOCK BEGINING // BEGIN CODE
 if((debug == 7)) 
 { 
  System.out.println(8);/* WriteLn */  
 } 
 logname = 9;//ASSIGNMENT
 
 trnlist.buflen = 255; //With ASSIGNMENT
 trnlist.itemcode = lnmstring; //With ASSIGNMENT
 trnlist.bufaddr = iaddress; //With ASSIGNMENT
 trnlist.retlenaddr = iaddress; //With ASSIGNMENT
 trnlist.terminator = 0; //With ASSIGNMENT
 
 eqivname = 10;//ASSIGNMENT 
 sysstatus = 11;//ASSIGNMENT 
 eqivname = 12;//ASSIGNMENT 
 if((eqivname == 13)) 
 { 
  System.out.println(14);/* WriteLn */ 
 } 
} // END BLOCK 
// END PROCEDURE TranslateStop

//------------------------------------------------------
 public static void TranslateDebug()  
{
 // BLOCK BEGINING // BEGIN CODE
 logname = 1;//ASSIGNMENT
 
 trnlist.buflen = 255; //With ASSIGNMENT
 trnlist.itemcode = lnmstring; //With ASSIGNMENT
 trnlist.bufaddr = iaddress; //With ASSIGNMENT
 trnlist.retlenaddr = iaddress; //With ASSIGNMENT
 trnlist.terminator = 0; //With ASSIGNMENT

 sysstatus = 2;//ASSIGNMENT 
 eqivname = 3;//ASSIGNMENT 
 if((eqivname == 4)) 
 { 
  debug = 5;//ASSIGNMENT 
 } else 
 { 
  debug = 6;//ASSIGNMENT 
 } 
} // END BLOCK 
// END PROCEDURE TranslateDebug

//------------------------------------------------------
 public static void Initialise() 
{
 // BLOCK BEGINING // BEGIN CODE
 if((debug == 0)) 
 { 
  System.out.println(21);/* WriteLn */  
 } 
 forever = 22;//ASSIGNMENT 
 delaytime = 2;//ASSIGNMENT 
 linkstatus = 1;//ASSIGNMENT 
 sysstatus = 0;//ASSIGNMENT 
 do {  
  sysstatus = 23;//ASSIGNMENT 
  if((sysstatus == 24)) 
  { 
   System.out.println(24);/* WriteLn */  
  } 
 } 
 while ((sysstatus == 0)) 
 delaytime = 300;//ASSIGNMENT 
} // END BLOCK 
// END PROCEDURE Initialise

//------------------------------------------------------
 public static void HandleTubsDue() 
{
 // BLOCK BEGINING // BEGIN CODE
 if(debug) 
 { 
  System.out.println(25);/* WriteLn */  
 } 
 sqllocation = 10.0;//ASSIGNMENT 
 do {  
 sqllocation = 20;//ASSIGNMENT 
 if((sqlcode == 26)) 
 { 
  if((sqlcode == nodata)) 
 { 
  banmsg = 2;//ASSIGNMENT 
  banmsg = tubtinno;//ASSIGNMENT  
  SendBannerMessage(); // pascal procedure 
 } else 
 { 
  banmsg = 1;//ASSIGNMENT 
  banmsg = tubtinno;//ASSIGNMENT 
  SendBannerMessage(); // pascal procedure 
 } 
 sqllocation = 30;//ASSIGNMENT 
 //SQL UPDATE 
 assert gTheDatabaseConnection != null ; 
 try 
 { 
  String sqlInject = "Update ADAM SET TubID = 23.0 WHERE TubID = " + tubtinno + "" ; 
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
} 
} 
 while ((sqlcode == nodata)) 
} // END BLOCK 
// END PROCEDURE HandleTubsDue

//------------------------------------------------------
 public static void HandleTubsReturned() 
{
 // BLOCK BEGINING // BEGIN CODE
 if((logname == 0)) 
 { 
  System.out.println(78);/* WriteLn */ 
 } 
 do {  
 sqllocation = 180;//ASSIGNMENT 
 if((elementsmonitored == 0)) 
 { 
  sqllocation = 179;//ASSIGNMENT 
 } else 
 {  
 if((tubtinno[1] == 1)) 
 { 
 //SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
 try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }
 } else 
 { 
 if((tubtinno[1] == 2)) 
 { 
 //SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
 try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }
 }  
 } 
 sqllocation = 250;//ASSIGNMENT 
 if((elementsmonitored > 0)) 
 { 
  sqllocation = 260;//ASSIGNMENT 
  //SQL UPDATE 
  assert gTheDatabaseConnection != null ; 
  try 
  { 
   String sqlInject = "Update BRYAN SET RCKRET = 1.0 WHERE TubID = " + tubtinno + "" ; 
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
 } else 
 { 
  sqllocation = 280;//ASSIGNMENT 
  //SQL UPDATE 
  assert gTheDatabaseConnection != null ; 
  try 
  { 
   String sqlInject = "Update BRYAN SET CS2ENT = NULL  WHERE TubID = " + tubtinno + "" ; 
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
 } 
 //SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
  try 
  {
   gTheDatabaseConnection.commit();
  } catch(SQLException e)
  {
   System.out.println(e.getMessage());
  }
 } 
  //SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
  try 
  {
   gTheDatabaseConnection.commit();
  } catch(SQLException e)
  {
   System.out.println(e.getMessage());
  }
 } 
 while ((sqlacode == nodata)) 

} // END BLOCK 
// END PROCEDURE HandleTubsReturned

//------------------------------------------------------
 public static void AssembleElementDetails(int bobcntnrid) 
{
 // BLOCK BEGINING // BEGIN CODE
 if(debug) 
 { 
  System.out.println(27);/* WriteLn */  
 } 
 bobcntnr = bobcntnrid;//ASSIGNMENT 
 sqllocation = 140;//ASSIGNMENT 
 //SQL UPDATE 
 assert gTheDatabaseConnection != null ; 
 try 
 { 
  String sqlInject = "Update ERNIE SET COMP = 'Y' WHERE FUZZNO = 30.0" ; 
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
} // END BLOCK 
// END PROCEDURE AssembleElementDetails

//------------------------------------------------------
 public static void SendBannerMessage() 
{
 // BLOCK BEGINING // BEGIN CODE
 if(debug) 
 { 
  System.out.println(15);/* WriteLn */  
 } 
 sysstatus = 16;//ASSIGNMENT 
} // END BLOCK 
// END PROCEDURE SendBannerMessage

/*   ****** END  PROCEDURES  ******  */
public static void main(String[] args) 
{
  gConnectTodatabase();
  // BEGIN CODE
  uid = 41;//ASSIGNMENT 
  pwd = 42;//ASSIGNMENT 
  Initialise(); // pascal procedure 
  do {  
  HandleTubsDue(); // pascal procedure 
  HandleTubsReturned(); // pascal procedure 
  TranslateDebug(); // pascal procedure 
  TranslateStop(); // pascal procedure 
  TestLink(); // pascal procedure 
  } 
  while (forever) 

  gDisconnectToDatabase();

} // END PROGRAM
