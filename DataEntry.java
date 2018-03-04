package biz.analytics;
import java.sql.*;
import java.util.Random;
public class DataEntry 
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "0";
    public static void main(String[] args) 
    {
        Connection conn = null;
        Statement stmt = null;
        int x=0;
        
      Random r = new Random();
      int Low = 0,Low1=1;
      int High = 6,High1=28;
      int count=0,count1=0;
      int a[]=new int[10];
      try
      {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      for(int month=1;month<=12;month++)
      {
      for(int cust=1;cust<=10;cust++)
      {
          for(int i=1;i<=5;i++)
          {
              for(int y=0;y<10;y++){ a[y]=(r.nextInt(High-Low) + Low);}
              int dat=(r.nextInt(High1-Low1) + Low1);
          String sql = "insert into maintable values ("+cust+","+a[0]+","+a[1]+","+a[2]+","+a[3]+","+a[4]+","+a[5]+","+a[6]+","+a[7]+","+a[8]+","+a[9]+", DATE '2017-"+month+"-"+dat+"')";
          int q =stmt.executeUpdate(sql);
          sql="insert into cust"+cust+" values ("+a[0]+","+a[1]+","+a[2]+","+a[3]+","+a[4]+","+a[5]+","+a[6]+","+a[7]+","+a[8]+","+a[9]+", DATE '2017-"+month+"-"+dat+"')";
          int q1 =stmt.executeUpdate(sql);
          count=count+q;
          count1=count1+q1;
          }
      }
      }
          System.out.println(count+" "+count1);
      stmt.close();
      conn.close();
      }
      catch(SQLException | ClassNotFoundException se)
      {
            //Handle errors for JDBC

      }
        //Handle errors for Class.forName
        finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }
        }
    
}


        