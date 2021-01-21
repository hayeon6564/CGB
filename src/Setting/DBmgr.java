package Setting;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DBmgr {

   public static String sql;

   DBcon conn;

   static Connection con = null;
   static Statement stmt = null;
   static ResultSet rs = null;

   public DBmgr() {

      conn =new DBcon();

   }

   public ArrayList<Bean> user(){

      ArrayList<Bean> list = new ArrayList<Bean>();
      Bean bean;

      try {

         con = conn.getConnection();
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);

         while(rs.next()) {

            bean = new Bean();

            bean.setUno(rs.getInt(1));
            bean.setUid(rs.getString(2));
            bean.setUpw(rs.getString(3));
            bean.setUname(rs.getString(4));
            Date d = rs.getDate(5);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String date = sdf.format(d);
            bean.setUbd(date);
            bean.setUpoint(rs.getInt(6));
            bean.setUgrade(rs.getString(7));

            list.add(bean);

         }

      }catch(Exception e) {

      }

      return list;

   }

   public ArrayList<Bean> movie(){

      ArrayList<Bean> list = new ArrayList<Bean>();
      Bean bean;

      try {

         con = conn.getConnection();
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);

         while(rs.next()) {

            bean = new Bean();

            bean.setMno(rs.getInt(1));
            bean.setMgenre(rs.getString(2));
            bean.setMname(rs.getString(3));
            bean.setMage(rs.getString(4));
            bean.setMprice(rs.getInt(5));
            Date d = rs.getDate(6);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(d);
            bean.setMdate(date);
            bean.setMtime(rs.getString(7));

            list.add(bean);

         }

      }catch(Exception e) {

      }

      return list;

   }

   public ArrayList<Bean> orderlist(){

      ArrayList<Bean> list = new ArrayList<Bean>();
      Bean bean;

      try {

         con = conn.getConnection();
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);

         while(rs.next()) {

            bean = new Bean();

            bean.setOno(rs.getInt(1));
            bean.setOdate(rs.getString(2));
            bean.setUno(rs.getInt(3));
            bean.setMno(rs.getInt(4));
            bean.setOgenre(rs.getString(5));
            bean.setOprice(rs.getInt(6));

            list.add(bean);

         }

      }catch(Exception e) {

      }

      return list;

   }

   public ArrayList<Bean> shoplist(){

      ArrayList<Bean> list = new ArrayList<Bean>();
      Bean bean;

      try {

         con = conn.getConnection();
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);

         while(rs.next()) {

            bean = new Bean();

            bean.setSno(rs.getInt(1));
            bean.setUno(rs.getInt(2));
            bean.setMno(rs.getInt(3));
            list.add(bean);

         }

      }catch(Exception e) {

      }

      return list;

   }
   public ArrayList<Bean> staff(){
      
      ArrayList<Bean> list = new ArrayList<Bean>();
      Bean bean;
      
      try {
         
         con = conn.getConnection();
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);

         while(rs.next()) {

            bean = new Bean();

            bean.setStno(rs.getInt(1));
            bean.setMno(rs.getInt(2));
            bean.setStdirec(rs.getString(3));
            bean.setStactor1(rs.getString(4));
            bean.setStrole1(rs.getString(5));
            bean.setStactor2(rs.getString(6));
            bean.setStrole2(rs.getString(7));
            bean.setStactor3(rs.getString(8));
            bean.setStrole3(rs.getString(9));

            list.add(bean);
         }
            
      }catch(Exception e) {

      }

      return list;
   }

   public ArrayList<Bean> orderjoin(){

      ArrayList<Bean> list = new ArrayList<Bean>();
      Bean bean;

      try {

         con = conn.getConnection();
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);

         while(rs.next()) {

            bean = new Bean();

            bean.setOdate(rs.getString(1));
            bean.setMname(rs.getString(2));
            bean.setOprice(rs.getInt(3));

            list.add(bean);

         }

      }catch(Exception e) {

      }

      return list;

   }

   public ArrayList<Bean> basketjoin(){

      ArrayList<Bean> list = new ArrayList<Bean>();
      Bean bean;

      try {

         con = conn.getConnection();
         stmt= con.createStatement();
         rs = stmt.executeQuery(sql);

         while(rs.next()) {

            bean = new Bean();

            bean.setMname(rs.getString(1));
            bean.setMgenre(rs.getString(2));
            bean.setMage(rs.getString(3));
            bean.setMprice(rs.getInt(4));

            list.add(bean);

         }

      }catch(Exception e) {

      }

      return list;

   }

   public ArrayList<Bean> update(){

      ArrayList<Bean> list = new ArrayList<Bean>();

      try {

         con = conn.getConnection();
         stmt = con.createStatement();
         stmt.executeUpdate(sql);

      }catch(Exception e) {

      }

      return list;

   }

   public ArrayList<Bean> chartjoin(){

      ArrayList<Bean> list = new ArrayList<Bean>();
      Bean bean;

      try {

         con = conn.getConnection();
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);

         while(rs.next()) {

            bean = new Bean();

            bean.setMname(rs.getString(1));
            bean.setMno(rs.getInt(2));

            list.add(bean);

         }

      }catch(Exception e) {

      }

      return list;

   }

}