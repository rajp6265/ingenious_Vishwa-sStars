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
public class calcflagforcust {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "0";
    public static void main(String[] args) {
         Connection conn = null;
        Statement stmt = null;
        String sql;
        int  first, second, third;
        int a[]=new int[10];
        int count=0;
        int b[]=new int[10];
        try
      {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      for(int cust=1;cust<=10;cust++)
      {
      for(int month=1;month<=12;month++)
      {
          sql="select * from optforcust"+cust+" where month="+month+"";
          ResultSet rs=stmt.executeQuery(sql);
          rs.next();          
          for(int pro=1;pro<=10;pro++)
          {
               a[pro-1]=Integer.parseInt(rs.getString(pro+1));
          }
          int max=-100;
          third = first = second = Integer.MAX_VALUE;
          for (int i = 0; i < 10 ; i ++)
        {  
            if(a[i]>max)
            {
                max=a[i];
            }
            if (a[i] < first)
            {
                third = second;
                second = first;
                first = a[i];
            }
       
            else if (a[i] < second)
            {
                third = second;
                second = a[i];
            }
       
            else if (a[i] < third)
                third = a[i];
        }
          for(int i=1;i<=10;i++)
          {
              if(a[i-1]==first||a[i-1]==second||a[i-1]==third)
              {
                  b[i-1]=3;
              }
              else if(a[i-1]==max)
              {
                  b[i-1]=2;
              }
              else
              {
                  b[i-1]=1;
              }
          }
          sql=sql="insert into flagforcust"+cust+" values ("+month+","+b[0]+","+b[1]+","+b[2]+","+b[3]+","+b[4]+","+b[5]+","+b[6]+","+b[7]+","+b[8]+","+b[9]+")";
          int z=stmt.executeUpdate(sql);
          count=count+z;
      
      }
      }
          System.out.println(count);
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

