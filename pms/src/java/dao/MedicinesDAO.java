/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Medicine;
import java.sql.SQLException;
import util.SQLQueryUtil;

/**
 *
 * @author babu
 */
public class MedicinesDAO {
    
    public int registerMedicine(Medicine medicine,String companyId) {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);
        int rowsAffected = 0;
        
        String insertQuery = "insert into pms_schema.medicines (medicine_name,company_id)"
                + " values('" + medicine.getMedicineName()  + "'," + companyId + ");";
        try{
           rowsAffected = sql.executeUpdate(insertQuery);
           sql.commit();
            System.out.println(insertQuery);
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return rowsAffected;
    }
}
