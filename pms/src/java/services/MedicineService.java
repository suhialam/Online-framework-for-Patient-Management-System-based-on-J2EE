/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MedicinesDAO;
import entity.Medicine;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.SQLQueryUtil;

/**
 *
 * @author babu
 */
public class MedicineService {
    private MedicinesDAO medicinesDAO;

    public MedicineService() {
        medicinesDAO = new MedicinesDAO();
    }
    
    
    
    public int registerMedicine(Medicine medicine,String companyId) {
        int rowsAffected = 0;
        int count = 0;
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);
        
        String queryCheck = "SELECT count(*) as count from pms_schema.medicines where medicine_name like("
                + "'"+medicine.getMedicineName()+"');";
        
        System.out.println(queryCheck);
        try{
            ResultSet resultSet = sql.executeQuery(queryCheck);
            resultSet.next();
            
            count = resultSet.getInt("count");
            
            if(count==0){
                rowsAffected = medicinesDAO.registerMedicine(medicine, companyId);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            sql.disconnect();
        }
       return rowsAffected;
    }
    public int updateMedicine(Medicine medicine){
        return medicinesDAO.updateMedicine(medicine);
        
    }
        
    public int addMedicineDetail(Medicine medicine){
        int rowsAffected = 0;
        rowsAffected = medicinesDAO.addMedicineDetail(medicine);
        return rowsAffected;
    }
}
