/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.SQLQueryUtil;

/**
 *
 * @author babu
 */
public class LoginDAO {
    
     public boolean tryLogin(User user){
         SQLQueryUtil sql = new SQLQueryUtil();
         sql.connect(false);
         
         Boolean value = false;
         
         String query = "Select count(*) as count from pms_schema.users where "
                 + "user_name like('" + user.getUserName() + "') and "
                 + "password like('" + user.getPassword() + "');";
         try {
             ResultSet rs = sql.executeQuery(query);
             rs.next();
             int count = rs.getInt("count");
             if(count == 0) {
                 value = false;
                 
             } else {
                 value = true;
             }
         } catch(SQLException ex) {
             ex.printStackTrace();
         } finally{
             sql.disconnect();
         }
         
         return value;
    }
}
