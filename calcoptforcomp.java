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
public class calcoptforcomp {
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
      int a[]=new int[13];
      int b[]=new int[13];
      int c[]=new int[13];
      int count=0,count1=0;
      for(int i=1;i<=10;i++)
      {
          sql="select avg(P"+i+") from sumforstock";
          ResultSet rs=stmt.executeQuery(sql);
          rs.next();
          float f=Float.parseFloat(rs.getString(1));
          a[i-1]=(int)f;
          //System.out.println(a[i-1]+"");
      }
          /*System.out.println("here");
      sql="select * from sumforstock where month="+1+"";
          ResultSet rs=stmt.executeQuery(sql);
          rs.next();     
          for(int pro=1;pro<=10;pro++)
          {
               b[pro-1]=Integer.parseInt(rs.getString(pro+1));
               System.out.println((pro-1)+"  "+b[pro-1]+"");
               //c[pro-1]=(11/12)*b[pro-1]+(1/12)*a[pro-1];
               //count1++;
          }*/
      for(int month=1;month<=12;month++)
      {
          sql="select * from sumforstock where month="+month+"";
          ResultSet rs=stmt.executeQuery(sql);
          rs.next();          
          for(int pro=1;pro<=10;pro++)
          {
               b[pro-1]=Integer.parseInt(rs.getString(pro+1));
               c[pro-1]=((11*b[pro-1])+a[pro-1])/12;
               count1++;
          }
          
          sql="insert into optforstock values ("+month+","+c[0]+","+c[1]+","+c[2]+","+c[3]+","+c[4]+","+c[5]+","+c[6]+","+c[7]+","+c[8]+","+c[9]+")";
          //System.out.println(sql);
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
