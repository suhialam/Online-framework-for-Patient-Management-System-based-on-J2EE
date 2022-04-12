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
 * @author suhail
 */
public class CompaniesDAO {

    public int registerCompany(Company company) {
        int rowsAffected = 0;

        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String queryInsert = "INSERT INTO company (company_name, address, phone_number) "
                + "values('" + company.getCompanyName() + "', '" + company.getAddress() + "', '" + company.getPhoneNumber() + "');";

        System.out.println(queryInsert);

        try {
            rowsAffected = sql.executeUpdate(queryInsert);
            sql.commit();
            System.out.println("Company registered successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return rowsAffected;
    }

    public int updateCompany(Company company) {
        int rowsAffected = 0;
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        String queryInsert = "UPDATE company  SET company_name='"
                 + company.getCompanyName() + "', address='" + company.getAddress() + "', phone_number='" + company.getPhoneNumber()
                + "' WHERE company_id=" + company.getCompanyId() + ";";

        System.out.println(queryInsert);

        try {
            rowsAffected = sql.executeUpdate(queryInsert);
            sql.commit();
            System.out.println("Company updated successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        
        return rowsAffected;
        
    }

}
