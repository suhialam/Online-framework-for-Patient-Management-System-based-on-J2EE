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
 * @author suhail
 */
public class MedicinesDAO {

    public int registerMedicine(Medicine medicine, String companyId) {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);
        int rowsAffected = 0;

        String insertQuery = "insert into medicines (medicine_name,company_id)"
                + " values('" + medicine.getMedicineName() + "'," + companyId + ");";
        try {
            rowsAffected = sql.executeUpdate(insertQuery);
            sql.commit();
            System.out.println(insertQuery);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return rowsAffected;
    }

    public int updateMedicine(Medicine medicine) {
        int rowAffected = 0;
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String Updatequery = "UPDATE medicines SET medicine_name = '" + 
                medicine.getMedicineName()+ "' WHERE medicine_id=" + medicine.getMedicineId()  +"; ";

        System.out.println(Updatequery);

        try {
            rowAffected = sql.executeUpdate(Updatequery);
            sql.commit();
            System.out.println(Updatequery);
            System.out.println("Medicine updated successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return rowAffected;
    }
    
    public int addMedicineDetail(String medicineId, String packing) {
        int rowsAffected = 0;
        
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);
        
        String query = "insert into medicine_detail (packing, medicine_id)"
                + " values('" + packing + "', " + medicineId + ");";
        System.out.println("query="+query);
        try {
           // System.out.println("try body");
            rowsAffected = sql.executeUpdate(query);
            sql.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return rowsAffected;
    }
 
}
