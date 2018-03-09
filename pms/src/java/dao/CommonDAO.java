/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Company;
import entity.Medicine;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.SQLQueryUtil;

/**
 *
 * @author babu
 */
public class CommonDAO {

    public List<Company> getListCompanies() {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        Company company;

        List<Company> listCompanies = new ArrayList<Company>();

        String query = "SELECT * FROM pms_schema.companies ORDER BY id ASC;";
        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                company = new Company();
                company.setCompanyId(rs.getInt("id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setAddress(rs.getString("address"));
                company.setPhoneNumber(rs.getString("phone_number"));

                listCompanies.add(company);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        
       
        return listCompanies;
    }

    public List<Medicine> getListMedicines() {
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);

        Medicine medicine;
        Company company = new Company();
        List<Medicine> listMedicines = new ArrayList<Medicine>();

        String query = "SELECT * FROM pms_schema.medicines where company_id="
                + company.getCompanyId()+ ";";
        try {
            ResultSet rs = sql.executeQuery(query);
            while (rs.next()) {
                medicine = new Medicine();
                medicine.setMedicineId(rs.getInt("id"));
                medicine.setMedicineName(rs.getString("medicine_name"));

                medicine.setCompany(company);
                listMedicines.add(medicine);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }

        return listMedicines;
    }

    public Company findCompany(String companyId) {
        Company company2 = new Company();
        
        SQLQueryUtil sql = new SQLQueryUtil();
        sql.connect(false);
        
        String query = "SELECT * FROM pms_schema.companies where id=" + companyId + ";";
    System.out.println(query);
        try {
            ResultSet resultSet = sql.executeQuery(query);
            resultSet.next();
            
            company2.setCompanyId(resultSet.getInt("id"));
            company2.setCompanyName(resultSet.getString("company_name"));
            company2.setAddress(resultSet.getString("address"));
            company2.setPhoneNumber(resultSet.getString("phone_number"));
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            sql.disconnect();
        }
        return company2;        
       }

}
