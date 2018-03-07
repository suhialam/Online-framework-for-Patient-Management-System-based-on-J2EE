/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Patient;
import java.sql.SQLException;
import util.SQLQueryUtil;

/**
 *
 * @author babu
 */
public class PatientsDAO {
    public int registerPatient(Patient patient) {
        int rowsAffected = 0;
        
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);
        
        String query = "INSERT INTO pms_schema.patients (patient_name, address, phone_number) "
                + "VALUES('" + patient.getPatientName() + "',"
                + "'" + patient.getAddress() + "',"
                + "'" + patient.getPhoneNumber() + "');";
        
        try {
            rowsAffected = sql.executeUpdate(query);
            sql.commit();
            System.out.println("Successfull.");
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        
        return rowsAffected;
    }
    
 
}
