/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenioushack;
import java.sql.*;
/**
 *
 * @author Ridham Dave
 */
public class CalcSumForComp {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "0";
    public static void main(String[] args) {
         Connection conn = null;
        Statement stmt = null;
        String sql;
        try
      {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      int a[]=new int[10];
      int count=0,count1=0;
      for(int month=1;month<=12;month++)
      {
          for(int pro=1;pro<=10;pro++)
          {
              sql="Select sum(P"+pro+") from maintable where ( select extract( month from tx_date)="+month+")";
              ResultSet rs=stmt.executeQuery(sql);
              rs.next();
              a[pro-1]=Integer.parseInt(rs.getString(1));
              count1++;
          }
          sql="insert into sumforstock values ("+month+","+a[0]+","+a[1]+","+a[2]+","+a[3]+","+a[4]+","+a[5]+","+a[6]+","+a[7]+","+a[8]+","+a[9]+")";
          int z=stmt.executeUpdate(sql);
          count=count+z;
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
