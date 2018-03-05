/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Company;
import java.sql.SQLException;
import util.SQLQueryUtil;

/**
 *
 * @author babu
 */
public class CompaniesDAO {
    
    public int registerCompany(Company company) {
        int rowsAffected = 0;
        
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);
        
        String queryInsert = "INSERT INTO pms_schema.companies (company_name, address, phone_number) " +
"values('" + company.getCompanyName() + "', '" + company.getAddress() + "', '" + company.getPhoneNumber() + "');";
        
        System.out.println(queryInsert);
        
        try {
            rowsAffected = sql.executeUpdate(queryInsert);
            sql.commit();
            System.out.println("Company registered successfully.");
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        
        return rowsAffected;
    }
    
}
