/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PatientsDAO;
import entity.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.SQLQueryUtil;

/**
 *
 * @author babu
 */
public class PatientService {
    
    private PatientsDAO patientsDAO;

    public PatientService() {
        patientsDAO = new PatientsDAO();
    }
    
    public int registerPatient(Patient patient) {
        int rowsAffected  = 0;
        int count = 0;
        
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);
        
        String query = "SELECT count(*) as count from pms_schema.patients "
                + "where patient_name "
                + "like('" + patient.getPatientName() + "') and address "
                + "like('" + patient.getAddress() + "');";
        
        try{
            ResultSet rs = sql.executeQuery(query);
            rs.next();
            
            count = rs.getInt("count");
            
            System.out.println(count);
            if(count == 0) {
               rowsAffected = patientsDAO.registerPatient(patient);
                
            }
            
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return rowsAffected;
    }
    
     public int updatePatient(Patient patient, String patientId) {
         return patientsDAO.updatePatient(patient,patientId);
     }
}
